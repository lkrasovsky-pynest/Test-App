package com.transistorapps.userstestapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val userId: Int,
    val albumId: Int,
    val name: String,
    val thumbnailUrl: String,
    val url: String
)

