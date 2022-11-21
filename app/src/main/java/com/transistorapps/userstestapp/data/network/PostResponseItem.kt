package com.transistorapps.userstestapp.data.network

import kotlinx.serialization.Serializable

@Serializable
data class PostResponseItem(
    val id: Int,
    val userId: Int,
    val body: String,
    val title: String
)