package com.example.aorgia.database.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profiles")
data class UserDb(
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "password")
    val password: String,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "linkToIcon")
    val linkToIcon: String,
    @PrimaryKey
    val id: Int = 0
//    val token: String
)
