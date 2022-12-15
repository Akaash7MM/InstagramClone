package com.example.domain.repository

import com.example.domain.entities.Message

interface MessageRepository {
    suspend fun getMessages(): List<Message>
}