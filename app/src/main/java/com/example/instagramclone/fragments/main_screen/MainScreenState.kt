package com.example.instagramclone.fragments.main_screen

import com.example.domain.entities.Post

sealed class MainScreenState {
    class Success(val postList: List<Post>) : MainScreenState()
    class Failure(val throwable: Throwable) : MainScreenState()
    object Loading : MainScreenState()
    object Empty : MainScreenState()
}
