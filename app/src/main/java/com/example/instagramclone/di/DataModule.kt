package com.example.instagramclone.di

import com.example.data.MessageApiImpl
import com.example.data.remote.MessageApi
import com.example.data.repository.MessageRepositoryImpl
import com.example.domain.repository.MessageRepository
import com.example.domain.usecases.GetMessagesUseCase
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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

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
    fun providesMessageRepository(messageApi: MessageApi): MessageRepository {
        return MessageRepositoryImpl(messageApi)
    }

    @Provides
    @Singleton
    fun providesMessagesUsecase(messageRepository: MessageRepository): GetMessagesUseCase {
        return GetMessagesUseCase(messageRepository)
    }
}
