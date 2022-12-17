package com.example.aorgia.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.aorgia.database.dao.ProfileDao
import com.example.aorgia.database.table.UserDb

@Database(entities = [UserDb::class], version = 1, exportSchema = false)
abstract class ProfileDatabase: RoomDatabase() {
    abstract fun profileDao(): ProfileDao
}