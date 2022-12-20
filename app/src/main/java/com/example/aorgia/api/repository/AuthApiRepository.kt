package com.example.aorgia.api.repository

import com.example.aorgia.api.AuthApi
import com.example.aorgia.data.api.AuthUser
import com.example.aorgia.data.api.LoginUser
import javax.inject.Inject

class AuthApiRepository @Inject constructor(
    private val authApi: AuthApi
) {
    suspend fun login(user: AuthUser) = authApi.login(user)

    suspend fun registration(loginUser: LoginUser) = authApi.registration(loginUser)
}