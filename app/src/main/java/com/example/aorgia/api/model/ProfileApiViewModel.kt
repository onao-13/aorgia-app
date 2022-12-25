package com.example.aorgia.api.model

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aorgia.api.firestore.UploadImage
import com.example.aorgia.api.repository.ProfileApiRepository
import com.example.aorgia.api.response.StatusCodeApi
import com.example.aorgia.app.storage.BucketStorage
import com.example.aorgia.data.User
import com.example.aorgia.data.api.UpdatedUser
import com.example.aorgia.data.local.LocalUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ProfileApiViewModel @Inject constructor(
    private val repository: ProfileApiRepository
): ViewModel() {
    val profileData = mutableStateOf(User(0, "", "", ""))
    val loading = mutableStateOf(false)
    val isSuccessful = mutableStateOf(false)
    val isTagExists = mutableStateOf(false)

    fun getProfile(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loading.value = true
            val data = repository.getProfile(email)
            if (data.isSuccessful) {
                data.body()?.let {
                    profileData.value = User(it.id, it.username, it.tag, it.linkToIcon)
                    isSuccessful.value = true
                }
            }
            loading.value = false
        }
    }

    fun updateProfile(
        username: MutableState<String>,
        tag: MutableState<String>,
        icon: MutableState<Uri?>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            loading.value = true

            icon.value?.let {
                UploadImage().uploadImage(
                    firebaseChild = BucketStorage.childUserIcons + UUID.randomUUID().toString(),
                    uri = it,
                    onCompleteTask = { task ->
                        val link = task.result.toString()
                        sendUpdatedData(username.value, tag.value, link)
                    },
                    onFailureTask = {
                        loading.value = false
                    }
                )
            }

            sendUpdatedData(username.value, tag.value, LocalUser.Data.icon.value)
        }
    }

    private fun sendUpdatedData(
        username: String,
        tag: String,
        link: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("send", "ok")

            val status = repository.updateProfile(
                User(LocalUser.Data.id.value, username, tag, link)
            )

            when (status.code()) {
                StatusCodeApi.SUCCESS.code -> {
                    loading.value = false
                    isTagExists.value = false
                    LocalUser().updateUserData(
                        username, tag, link
                    )
                }
                StatusCodeApi.CONFLICT.code -> {
                    loading.value = false
                    isTagExists.value = true
                }
            }
        }
    }
}