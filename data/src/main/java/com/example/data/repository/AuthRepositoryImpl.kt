package com.example.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthRepositoryImpl(
    val dataStore: DataStore<Preferences>
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
}
