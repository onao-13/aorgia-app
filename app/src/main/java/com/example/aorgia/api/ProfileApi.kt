package com.example.aorgia.api

import com.example.aorgia.data.api.UpdatedUser
import com.example.aorgia.data.User
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface ProfileApi {
    @GET("/api/account/get-data")
    suspend fun getProfile(@Query("email") email: String): Response<User>

    @PUT("/api/account/update-user-data")
    suspend fun updateUserData(@Body user: User): Response<ResponseBody>
}