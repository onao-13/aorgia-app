package com.example.aorgia.api.model

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aorgia.api.firestore.UploadImage
import com.example.aorgia.api.repository.AuthApiRepository
import com.example.aorgia.api.response.StatusCodeApi.*
import com.example.aorgia.app.storage.BucketStorage
import com.example.aorgia.data.api.AuthUser
import com.example.aorgia.data.api.Email
import com.example.aorgia.data.api.RegistrationUser
import com.example.aorgia.database.model.ProfileDbViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

//TODO: REFACTORING THIS
@HiltViewModel
class AuthApiViewModel @Inject constructor(
    private val repository: AuthApiRepository
) : ViewModel() {
    val isSuccessful = mutableStateOf(false)
    val loading = mutableStateOf(false)
    val isUserNotFound = mutableStateOf(false)
    val user = mutableStateOf(AuthUser("", ""))
    val isUserExist = mutableStateOf(false)

    /**
     * Login
     */
    fun login(
        email: MutableState<String>,
        password: MutableState<String>
    ) {
        val user = AuthUser(email.value, password.value)
        this.user.value = user

        viewModelScope.launch(Dispatchers.IO) {
            loading.value = true
            val response = repository.login(user)

            when (response.code()) {
                SUCCESS.code -> isSuccessful.value = true
                NOT_FOUND.code -> isUserNotFound.value = true
            }

            loading.value = false
        }
    }

    /**
     * Check email
     */
    fun checkEmail(
        email: MutableState<String>,
        password: MutableState<String>,
        username: MutableState<String>,
        imageUri: MutableState<Uri?>,
        tag: MutableState<String>,
        profileDbViewModel: ProfileDbViewModel
    ) {
        user.value = AuthUser(email.value, password.value)

        viewModelScope.launch(Dispatchers.IO) {
            loading.value = true
            val response = repository.checkEmail(Email(email.value))

            when (response.code()) {
                SUCCESS.code -> createAccount(
                    email,
                    password,
                    username,
                    imageUri,
                    tag,
                    profileDbViewModel
                )
                CONFLICT.code -> {
                    isUserExist.value = true
                    loading.value = false
                }
            }
        }
    }

    /**
     * Registration
     */
    private fun createAccount(
        email: MutableState<String>,
        password: MutableState<String>,
        username: MutableState<String>,
        imageUri: MutableState<Uri?>,
        tag: MutableState<String>,
        profileDbViewModel: ProfileDbViewModel
    ) {
        imageUri.value?.let {
            UploadImage().uploadImage(
                BucketStorage.childUserIcons,
                it,
                onCompleteTask = { task ->
                    val link = task.result.toString()

                    registration(
                        email, password, username, link, tag
                    )

                    profileDbViewModel.login(email.value, password.value)
                },
                onFailureTask = {
                    loading.value = false
                }
            )
        }
    }

    private fun registration(
        email: MutableState<String>,
        password: MutableState<String>,
        username: MutableState<String>,
        linkToIcon: String,
        tag: MutableState<String>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.registration(
                RegistrationUser(
                    email.value,
                    password.value,
                    username.value,
                    linkToIcon,
                    tag.value
                )
            )

            when (response.code()) {
                SUCCESS.code -> {
                    isSuccessful.value = true
                    loading.value = false
                }
            }
        }
    }

}
