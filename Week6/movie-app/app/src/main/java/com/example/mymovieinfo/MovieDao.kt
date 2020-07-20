package com.example.mymovieinfo

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_table")
    fun getAllMovies() : LiveData<MutableList<Movie>>

    @Query("SELECT * FROM movie_table WHERE id IN(:movieId)")
    fun getMovieById(movieId: Int) : Movie

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovie(movie: Movie)


    @Delete
    fun deleteMovie(movie: Movie)

    @Query("DELETE FROM movie_table")
    fun deleteAllMovies()
}