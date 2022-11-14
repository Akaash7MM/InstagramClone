package com.example.instagramclone.fragments.message_screen

import com.example.domain.entities.Message

sealed class MessageScreenState {
    class Success(val messageList: List<Message>) : MessageScreenState()
    class Failure(val throwable: Throwable) : MessageScreenState()
    object Loading : MessageScreenState()
    object Empty : MessageScreenState()
}
