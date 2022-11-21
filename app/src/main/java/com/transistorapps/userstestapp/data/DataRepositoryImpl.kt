package com.transistorapps.userstestapp.data

import android.util.Log
import com.transistorapps.userstestapp.data.db.dao.UsersDao
import com.transistorapps.userstestapp.data.network.ApiInterface
import com.transistorapps.userstestapp.domain.ApiError
import com.transistorapps.userstestapp.domain.ApiResult
import com.transistorapps.userstestapp.domain.DataRepository
import com.transistorapps.userstestapp.domain.UserUiModel
import com.transistorapps.userstestapp.domain.toError
import com.transistorapps.userstestapp.domain.toUserUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.net.UnknownHostException

class DataRepositoryImpl(
    private val usersDao: UsersDao,
    private val apiInterface: ApiInterface
) : DataRepository {
    override suspend fun syncData(): ApiResult {
        return try {
            val usersResponse = apiInterface.getUsers()
            usersDao.insertAllUsers(usersResponse.map { it.mapToUserEntity() })
            val postsResponse = apiInterface.getPosts()
            usersDao.insertAllPosts(postsResponse.map { it.mapToPostEntity() })
            ApiResult.Success
        } catch (exception: UnknownHostException) {
            Log.e(
                "DataRepositoryImpl-TAG", "syncData()\n" +
                        "UnknownHostException: ${exception.stackTraceToString()}"
            )
            ApiError.NoInternetConnection.toError()
        } catch (exception: Exception) {
            Log.e(
                "DataRepositoryImpl-TAG", "syncData()\n" +
                        "Exception: ${exception.stackTraceToString()}"
            )
            ApiError.Generic.toError()
        }
    }

    override fun getUsers(): Flow<List<UserUiModel>> {
        return usersDao.getAllUsersWithPosts().map { list ->
            list.map { it.toUserUiModel() }
        }
    }

    override suspend fun getUserWithPosts(userId: Int): UserUiModel? {
        return usersDao.getUserWithPosts(userId)?.toUserUiModel()
    }
}