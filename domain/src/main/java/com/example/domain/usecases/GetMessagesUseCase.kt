package com.example.domain.usecases

import com.example.domain.entities.Message
import com.example.domain.repository.MessageRepository
import com.example.domain.util.Resource
import com.example.domain.util.safeResult

class GetMessagesUseCase(
    private val messageRepository: MessageRepository
) {

    suspend operator fun invoke(): Resource<List<Message>> {
        val resultList = safeResult { messageRepository.getMessages() }

        return resultList
    }
}
