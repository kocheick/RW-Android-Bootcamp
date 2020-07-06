package com.example.mymovieinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val adapter = MovieAdapter(MovieStore.getAllMovies())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initMovie()

    }

    private fun initMovie() {
        movieListRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        movieListRecyclerView.adapter = adapter
    }
}