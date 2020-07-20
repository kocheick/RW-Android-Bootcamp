package com.example.mymovieinfo

import androidx.lifecycle.LiveData

class MovieRepository(private val movieDao: MovieDao) {

    fun getAllMovies(): LiveData<MutableList<Movie>> {
        return movieDao.getAllMovies()
    }

    suspend fun insert(movie: Movie) {
        movieDao.insertMovie(movie)
    }



    suspend fun deleteAll(){
        movieDao.deleteAllMovies()
    }

    suspend fun deleteMovie(movie: Movie){
        movieDao.deleteMovie(movie)
    }
}