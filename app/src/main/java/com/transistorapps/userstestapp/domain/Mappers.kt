package com.transistorapps.userstestapp.domain

import com.transistorapps.userstestapp.data.db.entities.PostEntity
import com.transistorapps.userstestapp.data.db.entities.UserEntityWithPosts

fun UserEntityWithPosts.toUserUiModel() = UserUiModel(
    userId = user.userId,
    albumId = user.albumId,
    name = user.name,
    thumbnailUrl = user.thumbnailUrl,
    url = user.url,
    posts = type.map { it.toPostUiModel() },
)

fun PostEntity.toPostUiModel() = PostUiModel(
    id = id,
    userId = userId,
    body = body,
    title = title
)
