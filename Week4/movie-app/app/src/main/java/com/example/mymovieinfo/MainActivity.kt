package com.example.mymovieinfo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

import kotlinx.android.synthetic.main.activity_main.*
import movies

class MainActivity : AppCompatActivity() {

    private val adapter = MovieAdapter(MovieStore.getAllMovies())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initMainView()
    }

    private fun initMainView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        movieListRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        movieListRecyclerView.adapter = adapter
        // attach swipe-to-delete to recycler view
        ItemTouchHelper(itemTouChCallBack).attachToRecyclerView(movieListRecyclerView)
    }


    private val itemTouChCallBack = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return false
        }
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            val position = viewHolder.adapterPosition
            val deletedMovie = movies[position]
            val deletedMovieName = getString(R.string.deleted_movie, deletedMovie.title)

            Snackbar.make(movieListRecyclerView, deletedMovieName, Snackbar.LENGTH_SHORT)
                .setAction("Undo", View.OnClickListener {
                    adapter.addMovieBack(position)
                    adapter.notifyItemInserted(position)
                    Toast.makeText(applicationContext, deletedMovieName, Toast.LENGTH_SHORT)
                        .show()
                }).show()
            adapter.deleteMovie(position)
            adapter.notifyItemRemoved(position)
        }
    }


}