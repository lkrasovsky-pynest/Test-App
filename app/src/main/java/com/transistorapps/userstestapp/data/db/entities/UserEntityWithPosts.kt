package com.transistorapps.userstestapp.data.db.entities

import androidx.room.Embedded
import androidx.room.Relation

data class UserEntityWithPosts(
    @Embedded val user: UserEntity,
    @Relation(parentColumn = "userId", entityColumn = "userId")
    val type: List<PostEntity>,
)