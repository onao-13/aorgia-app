package com.example.aorgia.database.repository

import com.example.aorgia.data.User
import com.example.aorgia.database.dao.ProfileDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val dao: ProfileDao) {
    fun getProfile() = dao.getProfile()
    suspend fun login(user: User) = dao.saveProfile(user)
    suspend fun update(user: User) = dao.updateProfile(user)
    suspend fun logout(user: User) = dao.logout(user)
}