package com.example.aorgia.data.local

import androidx.compose.runtime.mutableStateOf
import com.example.aorgia.data.User

class LocalUser {
    object Data {
        val username = mutableStateOf("")
        val icon = mutableStateOf("")
        val tag = mutableStateOf("")
        val id = mutableStateOf(0)
    }

    fun addUserData(user: User) {
        Data.id.value = user.id
        Data.username.value = user.username
        Data.icon.value = user.linkToIcon
        Data.tag.value = user.tag
    }

    fun updateUserData(
        username: String,
        tag: String,
        icon: String
    ) {
        Data.username.value = username
        Data.icon.value = icon
        Data.tag.value = tag
    }
}