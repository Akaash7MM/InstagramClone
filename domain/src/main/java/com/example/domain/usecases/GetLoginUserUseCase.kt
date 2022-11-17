package com.example.domain.usecases

import com.example.data.util.safeResult
import com.example.domain.repository.AuthRepository
import com.example.domain.util.Resource

class GetLoginUserUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Resource<Boolean> {
        return safeResult { authRepository.loginUser(email, password) }
    }
}
