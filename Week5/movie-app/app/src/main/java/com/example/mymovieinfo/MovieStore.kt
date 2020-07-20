package com.example.mymovieinfo

import movies

object MovieStore {

    val movess = movies
    fun getAllMovies()= movies

    fun getMovieById(id: Int) = movies.firstOrNull { it.id == id }
}