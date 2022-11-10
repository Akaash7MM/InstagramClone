package com.example.domain.usecases

import com.example.domain.repository.AuthRepository

class GetSaveDetailsUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String) = authRepository.saveLoginDetails(username, password)
}
