package com.example.mymovieinfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.RecyclerView

fun View.onClick(onClickAction: () -> Unit) {
    setOnClickListener { onClickAction() }
}

fun ViewGroup.inflate(@LayoutRes layoutRest: Int, attachToRoot: Boolean = false) : View {
    return LayoutInflater.from(context).inflate(layoutRest,this,attachToRoot)
}
