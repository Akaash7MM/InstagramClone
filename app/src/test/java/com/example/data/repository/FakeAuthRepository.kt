package com.example.data.repository

import com.example.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeAuthRepository(

) : AuthRepository {
    private val fakeDataStore = mutableMapOf<String, String>()
    private val fakeFirebaseAuth = mutableMapOf<String, String>()

    override suspend fun saveLoginDetails(key: String, data: String) {
        fakeDataStore[key] = data
    }

    override suspend fun fetchLoginDetails(username: String): Flow<String> {
        return flowOf(fakeDataStore[username] ?: "")
    }

    override suspend fun createUser(email: String, password: String): Boolean {
        fakeFirebaseAuth[email] = password
        return true
    }

    override suspend fun loginUser(email: String, password: String): Boolean {
        return fakeFirebaseAuth[email] == password
    }
}
