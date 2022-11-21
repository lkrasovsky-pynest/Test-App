package com.transistorapps.userstestapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.transistorapps.userstestapp.data.db.dao.UsersDao
import com.transistorapps.userstestapp.data.db.entities.PostEntity
import com.transistorapps.userstestapp.data.db.entities.UserEntity

@Database(
    entities = [UserEntity::class, PostEntity::class],
    version = AppDatabase.VERSION,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun radiosDao(): UsersDao

    companion object {
        const val VERSION = 1
        const val DB_NAME = "usersTestApp.db"
    }
}