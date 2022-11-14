package com.example.data.repository

import com.example.data.remote.dto.toMessage
import com.example.data.remote.MessageApi
import com.example.domain.entities.Message
import com.example.domain.repository.MessageRepository
import com.example.domain.util.Resource

class MessageRepositoryImpl(
    private val messageApi: MessageApi
) : MessageRepository {

    override suspend fun getMessages(): List<Message> {
        val result = messageApi.getMessages()

        when (result) {
            is Resource.Success -> {
                return result.data.map { it.toMessage() }
            }

            is Resource.Failure -> {
                throw result.throwable
            }
        }
    }
}
