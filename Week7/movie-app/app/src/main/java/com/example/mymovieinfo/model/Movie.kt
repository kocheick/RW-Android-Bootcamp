package com.example.mymovieinfo.model

import android.os.Build
import android.os.Parcelable
import android.text.Html
import android.text.Html.fromHtml
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mymovieinfo.model.networkmodel.Image
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@RequiresApi(Build.VERSION_CODES.N)
@Entity(tableName = "movie_table")
@JsonClass(generateAdapter = true)
@Parcelize
data class Movie(
    @field:Json(name = "id") @PrimaryKey var id: Int? = null,
    @field:Json(name = "name") @ColumnInfo(name = "title") val title: String,
    @field:Json(name = "summary") @ColumnInfo(name = "summary") var summary: String?="N/A",
    @field:Json(name = "premiered") @ColumnInfo(name = "release_date") val releaseDate: String?,
    //@ColumnInfo(name = "image")
    @field:Json(name = "runtime") @ColumnInfo(name = "duration") val duration: Int? = "N/A".toIntOrNull(),
    @ColumnInfo(name = "isFavorite") @Transient var isFavorite: Boolean = false,
    @field:Json(name = "image")@ColumnInfo(name = "url") val imageUrl: Image?
) : Parcelable {
    init {                // format the Json         file summary from Html to human-readable text

        summary?.let {
            summary = fromHtml(summary, Html.FROM_HTML_MODE_LEGACY).toString()
        }
    }
}