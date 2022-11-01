package com.example.instagramclone.di

import com.example.data.PostApi
import com.example.data.repository.PostRepositoryImpl
import com.example.data.util.AuthInterceptor
import com.example.data.util.Constants
import com.example.domain.repository.PostRepository
import com.example.domain.usecases.GetPostUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

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

    @Provides
    @Singleton
    fun providesOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
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
