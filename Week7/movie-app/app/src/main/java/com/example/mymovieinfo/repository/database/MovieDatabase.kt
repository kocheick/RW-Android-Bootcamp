package com.example.mymovieinfo.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mymovieinfo.model.Movie
import com.example.mymovieinfo.utils.DataConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import kotlinx.coroutines.launch


@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
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
                   // populateDatabase(db)
                }
            }
        }

//        private fun populateDatabase(db: MovieDao) {
//            scope.launch(Dispatchers.IO) {
//                val movies = MovieStore.getAllMovies()
//                for (movie in movies) {
//                    db.insertMovie(movie)
//                    println("${movie.title} added to DB")
//                }
//            }
//        }

    }

    @InternalCoroutinesApi
    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null


        fun getDatabase(context: Context, scope: CoroutineScope): MovieDatabase {
            val tempInstance =
                INSTANCE
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
                        .addCallback(
                            MovieDatabaseCallback(
                                scope
                            )
                        )
                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }


}


