package com.example.domain.usecases

import com.example.domain.repository.AuthRepository

class GetFetchDetailsUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(username: String) = authRepository.fetchLoginDetails(username)
}
