package com.example.instagramclone.fragments

import androidx.lifecycle.ViewModel
import com.example.domain.entities.Post
import com.example.domain.usecases.GetFetchPostsUseCase
import com.example.domain.usecases.GetPostUseCase
import com.example.domain.usecases.GetSavePostUseCase
import com.example.domain.util.Resource
import com.example.instagramclone.fragments.main_screen.MainScreenState
import com.example.instagramclone.util.ioScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val postUseCase: GetPostUseCase,
    private val savePostUseCase: GetSavePostUseCase,
    private val fetchPostsUseCase: GetFetchPostsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainScreenState>(MainScreenState.Empty)
    val uiState = _uiState.asStateFlow()

    init {
        fetchPosts()
        getPostList()
    }

    fun getPostList() {
        _uiState.value = MainScreenState.Loading
        ioScope {
            val result = postUseCase()
            when (result) {
                is Resource.Success -> {
                    result.data.collect() { postList ->
                        _uiState.value = MainScreenState.Success(postList.shuffled())
                    }
                }
                is Resource.Failure -> {
                    _uiState.value = MainScreenState.Failure(throwable = result.throwable)
                }
            }
        }
    }

    fun fetchPosts() {
        ioScope {
            fetchPostsUseCase()
        }
    }

    fun savePost(post: Post) {
        ioScope {
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
