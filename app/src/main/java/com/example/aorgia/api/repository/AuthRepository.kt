package com.example.aorgia.api.repository

import com.example.aorgia.api.AuthApi
import com.example.aorgia.data.AuthUser
import com.example.aorgia.data.User
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authApi: AuthApi
) {
    suspend fun login(user: AuthUser): Response<ResponseBody> {
        return authApi.login(user)
    }

    suspend fun registration(user: User): Response<ResponseBody> {
        return authApi.registration(user)
    }
}