package com.example.aorgia.database.dao

import androidx.room.*
import com.example.aorgia.data.User
import com.example.aorgia.database.table.UserDb
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {

    @Query("SELECT * FROM profiles LIMIT 1")
    fun getProfile(): Flow<UserDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProfile(user: UserDb)

    @Update
    suspend fun updateProfile(user: UserDb)

    @Delete
    suspend fun logout(user: UserDb)
}