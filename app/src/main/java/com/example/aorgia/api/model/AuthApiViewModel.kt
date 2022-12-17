package com.example.aorgia.api.model

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aorgia.api.repository.AuthRepository
import com.example.aorgia.api.response.StatusCodeApi.*
import com.example.aorgia.data.AuthUser
import com.example.aorgia.data.User
import com.example.aorgia.database.model.ProfileDbViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AuthApiViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val database: ProfileDbViewModel
) : ViewModel() {
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

    private fun registration(
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

    fun createAccount(
        imageUri: MutableState<Uri?>,
        email: MutableState<String>,
        password: MutableState<String>,
        username: MutableState<String>
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
                val link = "https://firebasestorage.googleapis.com${task.result.path.toString()}"
                registration(
                    email, password, username, link
                )
                database.login(email, password, username, link)
            }
        }
    }
}
