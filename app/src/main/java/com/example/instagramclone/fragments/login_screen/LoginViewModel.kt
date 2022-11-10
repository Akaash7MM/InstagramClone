package com.example.instagramclone.fragments.login_screen // ktlint-disable package-name

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.GetFetchDetailsUseCase
import com.example.domain.usecases.GetSaveDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getSaveDetailsUseCase: GetSaveDetailsUseCase,
    private val getFetchDetailsUseCase: GetFetchDetailsUseCase

) : ViewModel() {

    val userToken: MutableStateFlow<String> = MutableStateFlow("")

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
}
