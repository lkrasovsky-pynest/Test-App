package com.transistorapps.userstestapp.domain

data class UserUiModel(
    val userId: Int,
    val albumId: Int,
    val name: String,
    val thumbnailUrl: String,
    val url: String,
    val posts: List<PostUiModel>,
)