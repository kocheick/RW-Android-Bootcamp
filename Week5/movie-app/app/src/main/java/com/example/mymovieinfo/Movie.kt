package com.example.mymovieinfo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class Movie(
    @PrimaryKey var id: Int? = null,
    @ColumnInfo(name = "title") var title: String? = null,
    @ColumnInfo(name = "summary") var summary: String? = null,
    @ColumnInfo(name = "release_date")   var releaseDate: String? = null,
    @ColumnInfo(name = "image")  val image: Int,
    @ColumnInfo(name = "duration")  var duration: Int? = null
)
