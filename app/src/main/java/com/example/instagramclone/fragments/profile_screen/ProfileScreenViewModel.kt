package com.example.instagramclone.fragments.profile_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.GetSavedPostsUseCase
import com.example.domain.util.Resource
import com.example.instagramclone.util.ioScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val getSavedPostsUseCase: GetSavedPostsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileScreenState>(ProfileScreenState.Empty)
    val uiState = _uiState.asStateFlow()

    init {
        getSavedPosts()
    }

    fun getSavedPosts() {
        _uiState.value = ProfileScreenState.Loading
        ioScope {
            val result = getSavedPostsUseCase()
            when (result) {
                is Resource.Success -> {
                    _uiState.value = ProfileScreenState.Success(result.data)
                }
                is Resource.Failure -> {
                    Log.d("ProfileScreen", result.throwable.toString())
                    _uiState.value = ProfileScreenState.Failure(result.throwable)
                }
            }
        }
    }
}
