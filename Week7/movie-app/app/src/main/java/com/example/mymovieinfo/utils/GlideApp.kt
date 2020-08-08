package com.example.mymovieinfo.utils

import android.widget.ImageView
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule


@GlideModule
class MyAppGlideModule : AppGlideModule() {

    fun loadImage(imageView: ImageView, url: String)= GlideApp.with(
        imageView.context
    ).load(url).into(imageView)

}
