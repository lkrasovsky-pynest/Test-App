package com.transistorapps.userstestapp.data

import com.transistorapps.userstestapp.data.db.entities.PostEntity
import com.transistorapps.userstestapp.data.db.entities.UserEntity
import com.transistorapps.userstestapp.data.network.PostResponseItem
import com.transistorapps.userstestapp.data.network.UserResponseItem

fun UserResponseItem.mapToUserEntity() =
    UserEntity(
        userId,
        albumId,
        name,
        thumbnailUrl,
        url
    )


fun PostResponseItem.mapToPostEntity() =
    PostEntity(
        id,
        userId,
        body,
        title
    )
