package com.mvvm.posts.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mvvm.posts.BuildConfig
import com.mvvm.posts.data.db.dao.PostDao
import com.mvvm.posts.data.db.entities.PostEntity
import javax.inject.Singleton

@Database(
    entities = arrayOf(
            PostEntity::class
    ),
    version = BuildConfig.VERSION_CODE, exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao

    companion object {
        private val DB_NAME = "Posts.db"

        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var appDatabase: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = appDatabase
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).fallbackToDestructiveMigration().build()
                appDatabase = instance
                return instance
            }
        }
    }
}