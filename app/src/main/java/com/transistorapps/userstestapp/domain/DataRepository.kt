package com.transistorapps.userstestapp.domain

import kotlinx.coroutines.flow.Flow

interface DataRepository {
    suspend fun syncData(): ApiResult
    fun getUsers(): Flow<List<UserUiModel>>
    suspend fun getUserWithPosts(userId: Int): UserUiModel?
}