package com.example.instagramclone.di

import com.example.domain.repository.AuthRepository
import com.example.domain.repository.MessageRepository
import com.example.domain.repository.PostRepository
import com.example.domain.usecases.GetCreateUserUseCase
import com.example.domain.usecases.GetFetchDetailsUseCase
import com.example.domain.usecases.GetFetchPostsUseCase
import com.example.domain.usecases.GetLoginUserUseCase
import com.example.domain.usecases.GetMessagesUseCase
import com.example.domain.usecases.GetPostUseCase
import com.example.domain.usecases.GetSaveDetailsUseCase
import com.example.domain.usecases.GetSavePostUseCase
import com.example.domain.usecases.GetSavedPostsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
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
    fun providesGetSavedPostsUseCase(repository: PostRepository): GetSavedPostsUseCase {
        return GetSavedPostsUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesFetchPostsUseCase(repository: PostRepository): GetFetchPostsUseCase {
        return GetFetchPostsUseCase(repository)
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

    @Provides
    @Singleton
    fun providesMessagesUsecase(messageRepository: MessageRepository): GetMessagesUseCase {
        return GetMessagesUseCase(messageRepository)
    }
}