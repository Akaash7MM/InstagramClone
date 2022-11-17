package com.example.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun saveLoginDetails(key: String, data: String)
    suspend fun fetchLoginDetails(username: String): Flow<String>
    suspend fun createUser(email: String, password: String): Boolean
    suspend fun loginUser(email: String, password: String) : Boolean
}
