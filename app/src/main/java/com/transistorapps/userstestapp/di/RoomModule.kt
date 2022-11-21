package com.transistorapps.userstestapp.di

import android.app.Application
import androidx.room.Room
import com.transistorapps.userstestapp.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    @Singleton
    @Provides
    fun provideAppDatabase(application: Application): AppDatabase =
        Room.databaseBuilder(application, AppDatabase::class.java, AppDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideJobsDao(database: AppDatabase) = database.radiosDao()
}