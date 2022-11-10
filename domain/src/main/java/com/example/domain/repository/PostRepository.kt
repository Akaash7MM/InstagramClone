package com.example.domain.repository

import com.example.domain.entities.Post

interface PostRepository {

    suspend fun getPosts(): List<Post>
}
