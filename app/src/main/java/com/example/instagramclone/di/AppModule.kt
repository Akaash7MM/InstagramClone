package com.example.instagramclone.di

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.data.BuildConfig
import com.example.data.PostApi
import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.PostRepositoryImpl
import com.example.data.util.Constants
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.PostRepository
import com.example.domain.usecases.GetCreateUserUseCase
import com.example.domain.usecases.GetFetchDetailsUseCase
import com.example.domain.usecases.GetLoginUserUseCase
import com.example.domain.usecases.GetPostUseCase
import com.example.domain.usecases.GetSaveDetailsUseCase
import com.example.domain.usecases.GetSavePostUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlin.annotation.AnnotationRetention.BINARY

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(name = "account_details")

    @Provides
    @Singleton
    fun provideUserDataStorePreferences(
        @ApplicationContext applicationContext: Context
    ): DataStore<Preferences> {
        return applicationContext.userDataStore
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
    fun providesAuthRepository(dataStore: DataStore<Preferences>, firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(dataStore, firebaseAuth)
    }

    @Provides
    @Singleton
    fun providesPostUseCase(repository: PostRepository): GetPostUseCase {
        return GetPostUseCase(repository)
    }
    @Provides
    @Singleton
    fun providesSavePostUseCase(repository: PostRepository): GetSavePostUseCase {
        return GetSavePostUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesSaveDetailsUseCase(repository: AuthRepository): GetSaveDetailsUseCase {
        return GetSaveDetailsUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesFetchDetailsUseCase(repository: AuthRepository): GetFetchDetailsUseCase {
        return GetFetchDetailsUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesCreateUserUseCase(repository: AuthRepository): GetCreateUserUseCase {
        return GetCreateUserUseCase(repository)
    }
    @Provides
    @Singleton
    fun providesLoginUserUseCase(repository: AuthRepository): GetLoginUserUseCase {
        return GetLoginUserUseCase(repository)
    }
}

@Qualifier
@Retention(BINARY)
annotation class Auth

@Qualifier
@Retention(BINARY)
annotation class Logg