package com.example.mymovieinfo

import movies

object MovieStore {


    fun getAllMovies(): List<Movie> = movies

    fun getMovieById(id: Int) = movies.firstOrNull { it.id == id }
}