package com.example.mymovieinfo.viewmodel

import android.app.Application
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.mymovieinfo.model.Movie
import com.example.mymovieinfo.repository.MovieRepository
import com.example.mymovieinfo.repository.database.MovieDatabase
import com.example.mymovieinfo.repository.network.NetworkStatusChecker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.M)
@InternalCoroutinesApi
class MoviesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MovieRepository
    private lateinit var liveDataMovies: LiveData<MutableList<Movie>>


    private val networkStatusChecker by lazy {
        NetworkStatusChecker(
            application.getSystemService(ConnectivityManager::class.java)
        )
    }

    init {
        val movieDao = MovieDatabase.getDatabase(
            application,
            viewModelScope
        ).movieDao()

        repository = MovieRepository(movieDao)
        viewModelScope.launch {
            liveDataMovies = repository.getAllFavoriteMovies()

    }}

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

    fun searchMovie(movieToSearch: String) = liveData(Dispatchers.IO) {
        lateinit var lastSearchResult: MutableList<Movie>
        networkStatusChecker.performIfConnectedToInternet {
            val retrievedMovies = mutableListOf<Movie>()
            // Get list of Movies in this format [{Movie:{id..}},{Movie1{image..}},{Movie3{properties..}}]
           if (movieToSearch.isNotBlank()){
               val retrievedData = repository.searchMovie(movieToSearch)
               for (movieData in retrievedData) retrievedMovies.add(movieData.movie)
               emit(retrievedMovies)
           }
        }
    }
}