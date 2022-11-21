package com.example.instagramclone.fragments.profile_screen

import com.example.domain.entities.Post

sealed class ProfileScreenState {
    class Success(val savedPostList: List<Post>) : ProfileScreenState()
    class Failure(val throwable: Throwable) : ProfileScreenState()
    object Loading : ProfileScreenState()
    object Empty : ProfileScreenState()
}
