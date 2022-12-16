package com.example.aorgia.api.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aorgia.api.repository.AuthRepository
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

            if (code == 200) {
                isSuccessfulLogin.value = true
            } else if (code == 404) {
                isUserNotFound.value = true
            }
        }
    }

    fun registration(user: User) = viewModelScope.launch {  }
}
