package com.example.mymovieinfo.repository

import com.example.mymovieinfo.model.Movie
import com.example.mymovieinfo.repository.database.MovieDao
import com.example.mymovieinfo.repository.network.builApiService

class MovieRepository(private val movieDao: MovieDao) {
    private val service = builApiService()


    suspend fun getAllMovies() = movieDao.getMovies()

    suspend fun getAllFavoriteMovies() = movieDao.getFavoriteMovies()

    suspend fun insert(movie: Movie) = movieDao.insertMovie(movie)

    fun save(movie: Movie) = movieDao.updateMovie(movie)

   suspend fun deleteAll() = movieDao.deleteMovies()

   suspend fun deleteMovie(movie: Movie) = movieDao.removeMovie(movie)

   suspend fun searchMovie(movieToSearch: String) = service.searchMovie(movieToSearch)

}