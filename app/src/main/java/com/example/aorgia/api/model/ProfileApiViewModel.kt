package com.example.aorgia.api.model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aorgia.api.repository.ProfileApiRepository
import com.example.aorgia.data.api.LoginUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileApiViewModel @Inject constructor(
    private val repository: ProfileApiRepository
): ViewModel() {
    val profileData = mutableStateOf<LoginUser>(LoginUser("", "", "", ""))
    val loading = mutableStateOf(true)
    val isSuccessful = mutableStateOf(false)

    fun getProfile(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getProfile(email)
            if (data.isSuccessful) {
                data.body()?.let {
                    profileData.value = it
                    isSuccessful.value = true
                }
            }
            loading.value = false
        }
    }
}