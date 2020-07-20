package com.example.mymovieinfo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.*
import kotlinx.coroutines.internal.synchronized


@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    private class MovieDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {
        @InternalCoroutinesApi
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    println("Database created..!")
                    val db = database.movieDao()
                    populateDatabase(db)
                }
            }
        }

        private fun populateDatabase(db: MovieDao) {
            GlobalScope.launch(Dispatchers.IO) {
                val movies = MovieStore.getAllMovies()
                for (movie in movies) {
                    db.insertMovie(movie)
                    println("${movie.title} added to DB")
                }
            }
        }

    }

    @InternalCoroutinesApi
    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null


        fun getDatabase(context: Context, scope: CoroutineScope): MovieDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java,
                        "movie_db"
                    )
                        .addCallback(MovieDatabaseCallback(scope))
                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }


}


