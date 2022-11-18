package com.example.instagramclone.fragments

import MainScreenState
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.domain.entities.Post
import com.example.domain.usecases.GetPostUseCase
import com.example.domain.usecases.GetSavePostUseCase
import com.example.domain.util.Resource
import com.example.instagramclone.util.ioScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val postUseCase: GetPostUseCase,
    private val savePostUseCase: GetSavePostUseCase
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
                    _uiState.value = MainScreenState.Success(result.data)
                }
                is Resource.Failure -> {
                    _uiState.value = MainScreenState.Failure(throwable = result.throwable)
                }
            }
        }
    }

    fun savePost(post: Post) {
        ioScope {
            val result = savePostUseCase(post)
            when(result){
                is Resource.Success ->{
                    //Channel Toast
                }
                is Resource.Failure ->{

                }
            }
        }
    }
}
