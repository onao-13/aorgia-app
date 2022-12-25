package com.example.aorgia.api

import com.example.aorgia.data.api.AuthUser
import com.example.aorgia.data.api.Email
import com.example.aorgia.data.api.RegistrationUser
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
    suspend fun registration(@Body registrationUser: RegistrationUser): Response<ResponseBody>

    @POST("/api/auth/check-email")
    suspend fun checkEmail(@Body email: Email): Response<ResponseBody>
}