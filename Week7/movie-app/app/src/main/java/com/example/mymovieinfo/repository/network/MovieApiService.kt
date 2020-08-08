package com.example.mymovieinfo.repository.network

import com.example.mymovieinfo.model.networkmodel.MovieSearchResponseItem
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("/search/shows?")
    suspend fun searchMovie(@Query("q") movieToSearch: String) : List<MovieSearchResponseItem>

}