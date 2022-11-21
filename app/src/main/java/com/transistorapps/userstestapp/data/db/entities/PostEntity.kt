package com.transistorapps.userstestapp.data.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "posts_table",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = arrayOf("userId"),
            childColumns = arrayOf("userId"),
        )
    ],
    indices = [Index(value = ["userId"])]
)
data class PostEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val userId: Int,
    val body: String,
    val title: String
)
