package com.example.mymovieinfo

import androidx.room.*


@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_table")
    fun getAllMovies() : List<Movie>

    @Query("SELECT * FROM movie_table WHERE id =:movieId")
    fun getMovieById(movieId: Int) : Movie

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMovie(movie: Movie)

    @Delete
    fun deleteMovieById(movieId: Int)

    @Query("DELETE FROM movie_table")
    fun deleteAllMovies()
}