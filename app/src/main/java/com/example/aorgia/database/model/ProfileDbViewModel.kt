package com.example.aorgia.database.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aorgia.data.api.LoginUser
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
    private val _profile = MutableStateFlow<LoginUser>(LoginUser("", "", "", ""))
    val profile: StateFlow<LoginUser> = _profile.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getProfile().distinctUntilChanged()
                .collect { profile ->
                    if (profile == null) {
                        _profile.value = LoginUser("", "", "", "")
                    }
                    else {
                        _profile.value = LoginUser(
                            profile.email, profile.password,
                            profile.username, profile.linkToIcon
                        )


                    }
                }
        }
    }

    fun getProfile() = viewModelScope.launch { repository.getProfile() }
    fun login(
        loginUser: LoginUser
    ) = viewModelScope.launch {
        if (loginUser.email.isNotEmpty()) {
            repository.login(
                UserDb(
                    loginUser.email, loginUser.password, loginUser.username, loginUser.linkToIcon
                )
            )
        }
    }
    fun update(user: UserDb) = viewModelScope.launch { repository.update(user) }
    fun logout(user: UserDb) = viewModelScope.launch { repository.logout(user) }
}