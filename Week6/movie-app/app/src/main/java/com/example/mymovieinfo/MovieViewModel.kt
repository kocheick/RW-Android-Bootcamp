package com.example.mymovieinfo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class MovieViewModel(application: Application): AndroidViewModel(application) {

    private val repository: MovieRepository
    private var liveDataMovies : LiveData<MutableList<Movie>>

    init {
        val movieDao = MovieDatabase.getDatabase(application,viewModelScope).movieDao()

        repository = MovieRepository(movieDao)
        liveDataMovies = repository.getAllMovies()

    }


    fun getAllMovies() = liveDataMovies

    fun addMovie(movie: Movie) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(movie)
    }

    fun deleteMovie(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteMovie(movie)
}

         fun clearMovies() = viewModelScope.launch(Dispatchers.IO){
        repository.deleteAll()
    }

}