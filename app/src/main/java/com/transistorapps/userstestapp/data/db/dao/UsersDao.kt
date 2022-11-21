package com.transistorapps.userstestapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.transistorapps.userstestapp.data.db.entities.PostEntity
import com.transistorapps.userstestapp.data.db.entities.UserEntity
import com.transistorapps.userstestapp.data.db.entities.UserEntityWithPosts
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {
    @Transaction
    @Query("SELECT * FROM users_table")
    fun getAllUsersWithPosts(): Flow<List<UserEntityWithPosts>>

    @Transaction
    @Query("SELECT * FROM users_table WHERE userId = :userId")
    suspend fun getUserWithPosts(userId: Int): UserEntityWithPosts?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUsers(usersList: List<UserEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPosts(moviesList: List<PostEntity>)
}