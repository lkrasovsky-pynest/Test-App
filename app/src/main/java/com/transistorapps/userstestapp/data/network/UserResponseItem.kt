package com.transistorapps.userstestapp.data.network

import kotlinx.serialization.Serializable

@Serializable
data class UserResponseItem(
    val userId: Int,
    val albumId: Int,
    val name: String,
    val thumbnailUrl: String,
    val url: String
)