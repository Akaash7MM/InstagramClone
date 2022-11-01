package com.example.data.repository

import com.example.data.PostApi
import com.example.data.remote.dto.toPost
import com.example.data.util.safeResult
import com.example.domain.entities.Post
import com.example.domain.repository.PostRepository
import com.example.domain.util.Resource


class PostRepositoryImpl(
    val api: PostApi

) : PostRepository {
    override suspend fun getPosts(): Resource<List<Post>> {
        val result = safeResult {
            api.getPosts()
        }
        when (result) {
            is Resource.Success -> {
                val list = result.result.photos.map { it.toPost() }
                return Resource.Success(list)
            }
            is Resource.Failure -> {
                return Resource.Failure(result.throwable)
            }
        }
    }
}
