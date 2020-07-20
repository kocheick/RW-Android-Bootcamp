package com.example.mymovieinfo

import androidx.lifecycle.LiveData

class MovieRepository(private val movieDao: MovieDao) {

    fun getAllMovies(): LiveData<MutableList<Movie>> {
        return movieDao.getAllMovies()
    }

    fun insert(movie: Movie) {
        movieDao.insertMovie(movie)
    }



    fun deleteAll(){
        movieDao.deleteAllMovies()
    }

    fun deleteMovie(movie: Movie){
        movieDao.deleteMovie(movie)
    }
}