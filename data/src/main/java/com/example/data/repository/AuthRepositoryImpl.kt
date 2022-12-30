package com.example.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

public class AuthRepositoryImpl(
    val dataStore: DataStore<Preferences>,
    val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override suspend fun saveLoginDetails(key: String, data: String) {
        val usernameKey = stringPreferencesKey(key)
        dataStore.edit {
            it[usernameKey] = data
        }
    }

    override suspend fun fetchLoginDetails(username: String): Flow<String> {
        val usernameKey = stringPreferencesKey(username)
        return dataStore.data.map { prefs ->
            prefs[usernameKey].orEmpty()
        }
    }

    override suspend fun createUser(email: String, password: String): Boolean {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        return result.user != null
    }

    override suspend fun loginUser(email: String, password: String): Boolean {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        return result.user != null
    }
}
