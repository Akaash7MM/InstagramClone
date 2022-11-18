package com.example.domain.usecases

import com.example.domain.entities.Post
import com.example.domain.repository.PostRepository
import com.example.domain.util.Resource
import com.example.domain.util.safeResult

class GetPostUseCase(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(): Resource<List<Post>> {
        val result = safeResult { postRepository.getPosts() }

        when (result) {
            is Resource.Success -> {
                return Resource.Success(result.data)
            }
            is Resource.Failure -> {
                return Resource.Failure(result.throwable)
            }
        }
    }
}
