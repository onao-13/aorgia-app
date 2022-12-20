package com.example.aorgia.api.repository

import com.example.aorgia.api.ProfileApi
import javax.inject.Inject

class ProfileApiRepository @Inject constructor(private val profileApi: ProfileApi) {

    suspend fun getProfile(email: String) = profileApi.getProfile(email)
}