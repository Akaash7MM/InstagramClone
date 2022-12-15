package com.example.instagramclone.di

import android.util.Log
import androidx.viewbinding.BuildConfig
import com.example.data.MessageApiImpl
import com.example.data.PostApi
import com.example.data.remote.MessageApi
import com.example.data.util.Constants
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlin.annotation.AnnotationRetention.BINARY

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesPostApi(authRetrofit: Retrofit): PostApi {
        return authRetrofit.create(PostApi::class.java)
    }

    @Provides
    @Singleton
    fun providesMessagesApi(): MessageApi {
        return MessageApiImpl(
            client = HttpClient(Android) {
                install(Logging) {
                    logger = Logger.ANDROID
                    level = LogLevel.ALL
                }
                install(ContentNegotiation) {
                    json(
                        Json {
                            prettyPrint = true
                            isLenient = true
                            coerceInputValues = true
                            ignoreUnknownKeys = true
                            encodeDefaults = true
                        }
                    )
                }
            }
        )
    }

    @Provides
    @Singleton
    fun providesFirebaseAuthInstance(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun providesAuthRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Auth
    @Provides
    @Singleton
    fun providesAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
            request.addHeader("Authorization", Constants.API_KEY)
            chain.proceed(request.build())
        }
    }

    @Logg
    @Provides
    @Singleton
    fun providesLoggingInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            if (BuildConfig.DEBUG) {
                Log.d("Logging", request.url.toString())
            }
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(@Auth authInterceptor: Interceptor, @Logg logInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .addInterceptor(logInterceptor)
            .build()
    }
}

@Qualifier
@Retention(BINARY)
annotation class Auth

@Qualifier
@Retention(BINARY)
annotation class Logg
