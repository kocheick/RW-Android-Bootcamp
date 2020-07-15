package com.example.mymovieinfo

import movies

object MovieStore {


    fun getAllMovies()= movies

    fun getMovieById(id: Int) = movies.firstOrNull { it.id == id }
}