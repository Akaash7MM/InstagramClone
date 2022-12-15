package com.example.instagramclone.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.data.PostApi
import com.example.data.local.PostDatabase
import com.example.data.remote.MessageApi
import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.MessageRepositoryImpl
import com.example.data.repository.PostRepositoryImpl
import com.example.data.util.RealCoroutineDispatcherProvider
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.MessageRepository
import com.example.domain.repository.PostRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesPostRepository(api: PostApi, db: PostDatabase): PostRepository {
        return PostRepositoryImpl(api, db.postDao(), RealCoroutineDispatcherProvider())
    }

    @Provides
    @Singleton
    fun providesAuthRepository(dataStore: DataStore<Preferences>, firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(dataStore, firebaseAuth)
    }

    @Provides
    @Singleton
    fun providesMessageRepository(messageApi: MessageApi): MessageRepository {
        return MessageRepositoryImpl(messageApi)
    }
}
