package com.example.instagramclone.fragments.login_screen // ktlint-disable package-name

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.GetCreateUserUseCase
import com.example.domain.usecases.GetFetchDetailsUseCase
import com.example.domain.usecases.GetLoginUserUseCase
import com.example.domain.usecases.GetSaveDetailsUseCase
import com.example.domain.util.Resource
import com.example.instagramclone.util.ioScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getSaveDetailsUseCase: GetSaveDetailsUseCase,
    private val getFetchDetailsUseCase: GetFetchDetailsUseCase,
    private val getCreateUserUseCase: GetCreateUserUseCase,
    private val getLoginUserUseCase: GetLoginUserUseCase

) : ViewModel() {

    val userToken: MutableStateFlow<String> = MutableStateFlow("")
    val userCreated: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val userLoggedIn: MutableStateFlow<Boolean> = MutableStateFlow(false)

    init {
        userAuthChangeFlow()
    }

    fun saveDetails(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getSaveDetailsUseCase(username, password)
        }
    }

    fun getDetails(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getFetchDetailsUseCase(username).collect { password ->
                password.let { pass ->
                    userToken.emit(pass)
                }
            }
        }
    }

    fun createUser(email: String, password: String) {
        ioScope {
            val result = getCreateUserUseCase(email, password)
            when (result) {
                is Resource.Success -> {
                    userCreated.emit(result.data)
                }
                is Resource.Failure -> {
                    userCreated.emit(false)
                }
            }
        }
    }

    fun userAuthChangeFlow(): Flow<Boolean> {
        return callbackFlow {
            Firebase.auth.addAuthStateListener { auth ->
                trySend(auth.currentUser != null)
            }
            awaitClose()
        }
    }

    fun loginUser(email: String, password: String) {
        ioScope {
            val result = getLoginUserUseCase(email, password)
            when (result) {
                is Resource.Success -> {
                    userLoggedIn.emit(result.data)
                }
                is Resource.Failure -> {
                    Log.d("LoginViewModel", result.throwable.message!!)
                    userLoggedIn.emit(false)
                }
            }
        }
    }
}
