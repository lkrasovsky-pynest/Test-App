package com.transistorapps.userstestapp.di

import com.transistorapps.userstestapp.data.DataRepositoryImpl
import com.transistorapps.userstestapp.data.db.dao.UsersDao
import com.transistorapps.userstestapp.data.network.ApiInterface
import com.transistorapps.userstestapp.domain.DataRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {
    @Singleton
    @Provides
    fun provideDataRepository(
        usersDao: UsersDao,
        apiInterface: ApiInterface
    ): DataRepository = DataRepositoryImpl(usersDao, apiInterface)
}