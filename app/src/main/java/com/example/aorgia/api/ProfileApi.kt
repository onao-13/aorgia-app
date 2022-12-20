package com.example.aorgia.api

import com.example.aorgia.data.api.LoginUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface ProfileApi {
    @GET("/api/account/get-data")
    suspend fun getProfile(@Query("email") email: String): Response<LoginUser>
}