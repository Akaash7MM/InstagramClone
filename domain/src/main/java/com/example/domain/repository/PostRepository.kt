package com.example.domain.repository

import com.example.domain.entities.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    fun getPosts(): Flow<List<Post>>
    suspend fun fetchPosts()
    suspend fun savePost(post: Post): Boolean
    suspend fun getSavedPosts(): Flow<List<Post>>
}
