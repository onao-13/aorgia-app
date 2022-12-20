package com.example.aorgia.api.model

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aorgia.api.repository.AuthApiRepository
import com.example.aorgia.api.response.StatusCodeApi.*
import com.example.aorgia.data.api.AuthUser
import com.example.aorgia.data.api.LoginUser
import com.example.aorgia.database.model.ProfileDbViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AuthApiViewModel @Inject constructor(
    private val repository: AuthApiRepository
) : ViewModel() {
    /**
     * Login
     */
    val isSuccessfulLogin = mutableStateOf(false)
    val isUserNotFound = mutableStateOf(false)
    val loading = mutableStateOf(false)

    val loginUser = mutableStateOf(AuthUser("", ""))

    fun login(
        email: MutableState<String>,
        password: MutableState<String>
    ) {
        val user = AuthUser(email.value, password.value)
        loginUser.value = user

        viewModelScope.launch(Dispatchers.IO) {
            loading.value = true
            val response = repository.login(user)
            val code = response.code()

            if (code == SUCCESS.code) {
                isSuccessfulLogin.value = true

            } else if (code == NOT_FOUND.code) {
                isUserNotFound.value = true
            }

            loading.value = false
        }
    }

    /**
     * Registration
     */
    val isSuccessfulRegistration = mutableStateOf(false)
    val isUserExists = mutableStateOf(false)

    private fun registration(
        email: MutableState<String>,
        password: MutableState<String>,
        username: MutableState<String>,
        linkToIcon: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.registration(
                LoginUser(
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


    fun createAccount(
        imageUri: MutableState<Uri?>,
        email: MutableState<String>,
        password: MutableState<String>,
        username: MutableState<String>,
        profileDbViewModel: ProfileDbViewModel
    ) {
        val ref = Firebase.storage("gs://aorgia.appspot.com")
            .reference.child("user-icon/" + UUID.randomUUID().toString())
        ref.putFile(imageUri.value!!).continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            ref.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val link = task.result.toString()
                registration(
                    email, password, username, link
                )
                profileDbViewModel.login(LoginUser(email.value, password.value, username.value, link))
            }
        }
    }
}
