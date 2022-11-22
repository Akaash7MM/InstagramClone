package com.example.domain.repository

import com.example.domain.entities.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    suspend fun getPosts(): Flow<List<Post>>
    suspend fun savePost(post: Post): Boolean
    suspend fun getSavedPosts(): Flow<List<Post>>
}
