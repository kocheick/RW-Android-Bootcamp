package com.example.mymovieinfo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {

        @Volatile private var INSTANCE: MovieDatabase? = null
        private val LOCK = Any()

        @InternalCoroutinesApi
        fun getDatabase(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: buildDataBase(context).also { INSTANCE = it }
        }

        private fun buildDataBase(context: Context) = Room.databaseBuilder(context.applicationContext, MovieDatabase::class.java,"movie.db")
            .build()
    }
}