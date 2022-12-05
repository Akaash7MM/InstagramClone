package com.example.instagramclone.fragments.message_screen

import androidx.lifecycle.ViewModel
import com.example.domain.usecases.GetMessagesUseCase
import com.example.domain.util.Resource
import com.example.instagramclone.util.ioScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    private val messagesUseCase: GetMessagesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MessageScreenState>(MessageScreenState.Empty)
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value = MessageScreenState.Loading
        ioScope {
            getMessages()
        }
    }

    suspend fun getMessages() {
        val response = messagesUseCase.invoke()
        when (response) {
            is Resource.Success -> {
                _uiState.value = MessageScreenState.Success(response.data)
            }
            is Resource.Failure -> {
            }
        }
    }
}
