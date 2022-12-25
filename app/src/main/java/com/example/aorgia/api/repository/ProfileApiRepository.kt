package com.example.aorgia.api.repository

import com.example.aorgia.api.ProfileApi
import com.example.aorgia.data.User
import com.example.aorgia.data.api.UpdatedUser
import javax.inject.Inject

class ProfileApiRepository @Inject constructor(private val profileApi: ProfileApi) {
    suspend fun getProfile(email: String) = profileApi.getProfile(email)
    suspend fun updateProfile(user: User) = profileApi.updateUserData(user)
}