package com.example.domain.usecases

import com.example.domain.repository.PostRepository
import com.example.domain.util.safeResult

class GetFetchPostsUseCase(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke() = safeResult { postRepository.fetchPosts() }
}