package com.example.instagramclone.fragments

import MainScreenState
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.GetPostUseCase
import com.example.domain.util.Resource
import com.example.instagramclone.util.ioScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val postUseCase: GetPostUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainScreenState>(MainScreenState.Empty)
    val uiState = _uiState.asStateFlow()

    init {
        getPostList()
    }

    fun getPostList() {
        _uiState.value = MainScreenState.Loading
        ioScope {
            val result = postUseCase.invoke()
            when (result) {
                is Resource.Success -> {
                    _uiState.value = MainScreenState.Success(result.result)
                }
                is Resource.Failure -> {
                    _uiState.value = MainScreenState.Failure(throwable = result.throwable)
                }
            }
        }
    }
}
