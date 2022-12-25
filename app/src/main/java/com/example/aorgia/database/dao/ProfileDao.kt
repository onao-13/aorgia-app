package com.example.aorgia.database.dao

import androidx.room.*
import com.example.aorgia.database.table.UserDb
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {

    @Query("SELECT * FROM profiles LIMIT 1")
    fun getProfile(): Flow<UserDb>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveProfile(user: UserDb)

    @Delete
    suspend fun logout(user: UserDb)
}