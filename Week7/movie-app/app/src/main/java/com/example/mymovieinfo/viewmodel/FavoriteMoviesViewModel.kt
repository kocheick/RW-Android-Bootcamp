package com.example.mymovieinfo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mymovieinfo.model.Movie
import com.example.mymovieinfo.repository.MovieRepository
import com.example.mymovieinfo.repository.database.MovieDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class FavoriteMoviesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MovieRepository
    private lateinit var liveDataMovies: LiveData<MutableList<Movie>>

    init {
        val movieDao = MovieDatabase.getDatabase(
            application,
            viewModelScope
        ).movieDao()
        repository = MovieRepository(movieDao)
        viewModelScope.launch {
        liveDataMovies = repository.getAllFavoriteMovies()
       }

    }
    fun getAllMovies() = liveDataMovies

    fun addMovie(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(movie)
    }

    fun deleteMovie(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteMovie(movie)
    }

    fun clearMovies() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

    fun updateInDatabase(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        repository.save(movie)

    }
}