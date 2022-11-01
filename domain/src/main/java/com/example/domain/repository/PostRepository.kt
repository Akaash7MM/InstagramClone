package com.example.domain.repository

import com.example.domain.entities.Post
import com.example.domain.util.Resource

interface PostRepository {

    suspend fun getPosts(): Resource<List<Post>>
}
