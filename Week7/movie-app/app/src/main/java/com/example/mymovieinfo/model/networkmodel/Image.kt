package com.example.mymovieinfo.model.networkmodel


import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Image(
    val medium: String, // http://static.tvmaze.com/uploads/images/medium_portrait/76/191071.jpg
    val original: String // http://static.tvmaze.com/uploads/images/original_untouched/76/191071.jpg
) : Parcelable
