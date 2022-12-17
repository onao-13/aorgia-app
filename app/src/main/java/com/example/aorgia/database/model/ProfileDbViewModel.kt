package com.example.aorgia.database.model

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aorgia.data.User
import com.example.aorgia.database.repository.ProfileRepository
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
class ProfileDbViewModel @Inject constructor(private val repository: ProfileRepository): ViewModel() {
    private val _profile = MutableStateFlow<User>(User("", "", "", ""))
    val profile: StateFlow<User> = _profile.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getProfile().distinctUntilChanged()
                .collect { profile ->
                    if (profile == null) _profile.value = User("", "", "", "")
                    else _profile.value = User(
                        profile.email, profile.password,
                        profile.username, profile.linkToIcon
                    )
                }
        }
    }

    fun getProfile() = viewModelScope.launch { repository.getProfile() }
    fun login(
        email: MutableState<String>,
        password: MutableState<String>,
        username: MutableState<String>,
        linkToIcon: String
    ) = viewModelScope.launch {
        repository.login(
            UserDb(
                email.value, password.value, username.value, linkToIcon
            )
        )
    }
    fun update(user: UserDb) = viewModelScope.launch { repository.update(user) }
    fun logout(user: UserDb) = viewModelScope.launch { repository.logout(user) }
}