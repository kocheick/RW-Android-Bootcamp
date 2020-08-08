package com.example.mymovieinfo.utils

import androidx.room.TypeConverter
import com.example.mymovieinfo.model.networkmodel.Image


class DataConverter {

        @TypeConverter
        fun fromImage(item: Image?)= item?.medium.toString()


        @TypeConverter
        fun toImage(item: String?): Image? {
            return item?.let {
                Image(
                    it,
                    item
                )
            }
        }
    }
