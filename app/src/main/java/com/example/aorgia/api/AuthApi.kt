package com.example.aorgia.api

import com.example.aorgia.data.AuthUser
import com.example.aorgia.data.User
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import javax.inject.Singleton
import retrofit2.http.POST

@Singleton
interface AuthApi {
    @POST("/api/auth/login")
    suspend fun login(@Body user: AuthUser): Response<ResponseBody>

    @POST("/api/auth/registration")
    suspend fun registration(@Body user: User): Response<ResponseBody>
}