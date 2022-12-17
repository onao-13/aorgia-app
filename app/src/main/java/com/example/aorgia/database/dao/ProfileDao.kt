package com.example.aorgia.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.aorgia.data.User
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {

    @Query("SELECT * FROM profiles LIMIT 1")
    fun getProfile(): Flow<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProfile(user: User)

    @Update
    suspend fun updateProfile(user: User)

    @Delete
    suspend fun logout(user: User)
}