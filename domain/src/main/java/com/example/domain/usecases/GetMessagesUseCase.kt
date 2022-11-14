package com.example.domain.usecases

import com.example.domain.entities.Message
import com.example.domain.repository.MessageRepository
import com.example.domain.util.Resource

class GetMessagesUseCase(
    private val messageRepository: MessageRepository
) {

    suspend operator fun invoke(): Resource<List<Message>> {
        val list = messageRepository.getMessages()

        return Resource.Success(list)
    }
}
