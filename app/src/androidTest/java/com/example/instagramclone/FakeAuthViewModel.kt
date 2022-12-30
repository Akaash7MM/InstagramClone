package com.example.instagramclone

import androidx.lifecycle.ViewModel

class FakeAuthViewModel : ViewModel() {
    private val fakeFirebaseAuth = mutableMapOf<String, String>()

    suspend fun createUser(email: String, password: String): Boolean {
        fakeFirebaseAuth[email] = password
        return true
    }
    suspend fun loginUser(email: String, password: String): Boolean {
        return fakeFirebaseAuth[email] == password
    }
}
