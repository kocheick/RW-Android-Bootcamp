package com.example.mymovieinfo.repository.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mymovieinfo.model.Movie


@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_table")
   fun getMovies() : LiveData<MutableList<Movie>>

    @Query("SELECT * FROM movie_table WHERE isFavorite == :isFavorite")
    fun getFavoriteMovies(isFavorite: Boolean = true) : LiveData<MutableList<Movie>>

    @Query("SELECT * FROM movie_table WHERE id IN(:movieId)")
    fun getMovieById(movieId: Int) : Movie

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Update
    fun updateMovie(movie: Movie)

    @Delete
    fun removeMovie(movie: Movie)

    @Query("DELETE FROM movie_table")
    fun deleteMovies()
}