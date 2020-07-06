package com.example.mymovieinfo

data class Movie(
    var id: Int? = null,
    var title: String? = null,
    var summary: String? = null,
    var releaseDate: String? = null,
    val image: Int,
    var duration: Int? = null
)
