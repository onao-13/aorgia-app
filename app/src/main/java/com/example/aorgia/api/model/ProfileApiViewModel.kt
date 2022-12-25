package com.example.aorgia.api.model

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aorgia.api.repository.ProfileApiRepository
import com.example.aorgia.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileApiViewModel @Inject constructor(
    private val repository: ProfileApiRepository
): ViewModel() {
    val profileData = mutableStateOf(User(0, "", "", ""))
    val loading = mutableStateOf(false)
    val isSuccessful = mutableStateOf(false)

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
}