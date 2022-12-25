package com.example.aorgia.database.model

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aorgia.data.User
import com.example.aorgia.data.api.AuthUser
import com.example.aorgia.database.repository.ProfileDbRepository
import com.example.aorgia.database.table.UserDb
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileDbViewModel @Inject constructor(private val repository: ProfileDbRepository): ViewModel() {
    private val _profile = MutableStateFlow<AuthUser>(AuthUser("", ""))
    val profile: StateFlow<AuthUser> = _profile.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getProfile().distinctUntilChanged()
                .collect { profile ->
                    if (profile == null) {
                        _profile.value = AuthUser("", "")
                    }
                    else {
                        _profile.value = AuthUser(
                            profile.email,
                            profile.password
                        )
                    }
                }
        }
    }

    fun getProfile() = viewModelScope.launch { repository.getProfile() }
    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
        repository.login(
            UserDb(
                email,
                password
            )
        )
    }
    fun logout(
        email: String,
        password: String
    ) = viewModelScope.launch {
        repository.logout(UserDb(email, password))
    }
}