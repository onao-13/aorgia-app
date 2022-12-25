package com.example.aorgia.database.repository

import com.example.aorgia.database.dao.ProfileDao
import com.example.aorgia.database.table.UserDb
import javax.inject.Inject

class ProfileDbRepository @Inject constructor(private val dao: ProfileDao) {
    fun getProfile() = dao.getProfile()
    suspend fun login(user: UserDb) = dao.saveProfile(user)
    suspend fun logout(user: UserDb) = dao.logout(user)
}