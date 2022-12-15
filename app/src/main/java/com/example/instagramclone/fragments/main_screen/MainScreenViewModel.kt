package com.example.instagramclone.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.Post
import com.example.domain.usecases.GetFetchPostsUseCase
import com.example.domain.usecases.GetPostUseCase
import com.example.domain.usecases.GetSavePostUseCase
import com.example.domain.util.Resource
import com.example.instagramclone.util.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val postUseCase: GetPostUseCase,
    private val savePostUseCase: GetSavePostUseCase,
    private val fetchPostsUseCase: GetFetchPostsUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<ScreenState<List<Post>>> = MutableStateFlow(ScreenState.Empty)
    val uiState = _uiState.asStateFlow()

    init {
        fetchPosts()
        getPostList()
    }

    fun getPostList() {
        _uiState.value = ScreenState.Loading
        viewModelScope.launch {
            val result = postUseCase()
            when (result) {
                is Resource.Success -> {
                    result.data.collect() { postList ->
                        _uiState.value = ScreenState.Success(postList.shuffled())
                    }
                }
                is Resource.Failure -> {
                    _uiState.value = ScreenState.Failure(throwable = result.throwable)
                }
            }
        }
    }

    fun fetchPosts() {
        viewModelScope.launch {
            fetchPostsUseCase()
        }
    }

    fun savePost(post: Post) {
        viewModelScope.launch {
            val result = savePostUseCase(post)
            when (result) {
                is Resource.Success -> {
                    // Channel Toast
                }
                is Resource.Failure -> {
                }
            }
        }
    }
}
