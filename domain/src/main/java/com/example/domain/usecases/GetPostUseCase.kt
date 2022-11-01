package com.example.domain.usecases

import com.example.domain.repository.PostRepository

class GetPostUseCase(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke() = postRepository.getPosts()
}
