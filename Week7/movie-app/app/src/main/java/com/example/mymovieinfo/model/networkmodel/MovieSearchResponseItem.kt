package com.example.mymovieinfo.model.networkmodel


import com.example.mymovieinfo.model.Movie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieSearchResponseItem(
   @field:Json(name = "show") val movie: Movie
)