package com.example.aorgia.database.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profiles")
data class ProfilesTable(
    @PrimaryKey
    val id: Int,
    val email: String,
    val password: String,
    val username: String,
    val userIcon: String,
    val token: String
)
