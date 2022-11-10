package com.example.domain.usecases

import com.example.domain.entities.Post
import com.example.domain.repository.PostRepository
import com.example.domain.util.Resource

class GetPostUseCase(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(): Resource<List<Post>> {
        val list = postRepository.getPosts()
        return Resource.Success(list)
    }
}
