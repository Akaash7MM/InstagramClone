package com.example.instagramclone.di

import android.util.Log
import com.example.data.BuildConfig
import com.example.data.PostApi
import com.example.data.repository.PostRepositoryImpl
import com.example.data.util.Constants
import com.example.domain.repository.PostRepository
import com.example.domain.usecases.GetPostUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.annotation.AnnotationRetention.BINARY

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

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

    @Provides
    @Singleton
    fun providesPostApi(authRetrofit: Retrofit): PostApi {
        return authRetrofit.create(PostApi::class.java)
    }

    @Provides
    @Singleton
    fun providesPostRepository(api: PostApi): PostRepository {
        return PostRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesPostUseCase(repository: PostRepository): GetPostUseCase {
        return GetPostUseCase(repository)
    }
}

@Qualifier
@Retention(BINARY)
annotation class Auth

@Qualifier
@Retention(BINARY)
annotation class Logg

