package com.example.data.repository

import com.example.data.PostApi
import com.example.data.remote.dto.toPost
import com.example.domain.entities.Post
import com.example.domain.repository.PostRepository
import kotlinx.coroutines.flow.map

class PostRepositoryImpl(
    val api: PostApi
) : PostRepository {

    override suspend fun getPosts(): List<Post> {
        val response = api.getPosts()

        if (response.isSuccessful) {
            return response.body()!!.photos.map { it.toPost() }
        } else {
            return emptyList()
        }
    }
}
