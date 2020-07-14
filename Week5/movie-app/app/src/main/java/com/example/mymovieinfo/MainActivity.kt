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
import kotlinx.android.synthetic.main.activity_main.view.*
import movies

class MainActivity : AppCompatActivity() {

    private val adapter = MovieAdapter(MovieStore.getAllMovies().toMutableList())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initMainView()
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        //supportActionBar?.setDisplayShowHomeEnabled(false)

    }

    private fun initMainView() {
        movieListRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        movieListRecyclerView.adapter = adapter

        // attach swipe-to-delete to recycler view
        val touchHelper = ItemTouchHelper(itemTouChCallBack)
            .attachToRecyclerView(movieListRecyclerView)


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.user_state, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_logout -> {
                logOut()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun logOut() {

        val editor: Editor = getSharedPreferences("state", Context.MODE_PRIVATE).edit()
        editor.putBoolean("loggedStatus", false).apply()

        val intent = Intent(this, LoginActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        // keeps the main screen to jump to login screen onBackPressed
        super.onBackPressed()
        finishAffinity()
    }


    private val itemTouChCallBack = object :
        ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition

            val deletedMovie = movies[position]
            val deletedMovieName = getString(R.string.deleted_movie, deletedMovie.title)

            Snackbar.make(
                movieListRecyclerView,
                getString(R.string.deleted_movie, deletedMovieName),
                Snackbar.LENGTH_SHORT
            ).setAction("Undo", View.OnClickListener {
                    movies.add(position, deletedMovie)
                    adapter.addMovieBack(position)
                    adapter.notifyItemInserted(position)
                    //Snackbar.make(movieListRecyclerView,getString(R.string.re_added_movie,deletedMovie.title), Snackbar.LENGTH_SHORT)
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.re_added_movie, deletedMovie.title),
                        Toast.LENGTH_SHORT
                    ).show()
                }).show()

            movies.removeIf { movies.indexOf(it) == position }
            adapter.deleteMovie(position)
            adapter.notifyItemRemoved(position)
        }
    }


}