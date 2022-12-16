package com.example.aorgia.api.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aorgia.api.repository.AuthRepository
import com.example.aorgia.api.response.StatusCodeApi
import com.example.aorgia.api.response.StatusCodeApi.*
import com.example.aorgia.data.AuthUser
import com.example.aorgia.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {
    val isSuccessfulLogin = mutableStateOf(false)
    val isUserNotFound = mutableStateOf(false)

    fun login(email: MutableState<String>, password: MutableState<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.login(AuthUser(email.value, password.value))
            val code = response.code()

            if (code == SUCCESS.code) {
                isSuccessfulLogin.value = true
            } else if (code == NOT_FOUND.code) {
                isUserNotFound.value = true
            }
        }
    }

    val isSuccessfulRegistration = mutableStateOf(false)
    val isUserExists = mutableStateOf(false)

    fun registration(
        email: MutableState<String>,
        password: MutableState<String>,
        username: MutableState<String>,
        linkToIcon: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.registration(
                User(
                    email.value,
                    password.value,
                    username.value,
                    linkToIcon
                )
            )
            val code = response.code()

            if (code == SUCCESS.code) {
                isSuccessfulRegistration.value = true
            } else if (code == NOT_FOUND.code) {
                isUserExists.value = true
            }
        }
    }
}
