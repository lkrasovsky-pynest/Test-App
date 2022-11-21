package com.transistorapps.userstestapp.data.network

import retrofit2.http.GET

interface ApiInterface {
    @GET("posts")
    suspend fun getPosts(): List<PostResponseItem>

    @GET("users")
    suspend fun getUsers(): List<UserResponseItem>
}