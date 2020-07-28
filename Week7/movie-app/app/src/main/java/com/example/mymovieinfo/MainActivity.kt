package com.example.mymovieinfo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class MainActivity : AppCompatActivity() {

    lateinit var adapter: MovieAdapter
    lateinit var movies: MutableList<Movie>
    lateinit var movieViewModel: MovieViewModel

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupAppContent()
    }

    private fun setupAppContent() {
        adapter = MovieAdapter(mutableListOf())
        movieListRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        movieListRecyclerView.adapter = adapter
        // attach swipe-to-delete to recycler view
        ItemTouchHelper(itemTouChCallBack).attachToRecyclerView(movieListRecyclerView)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.getAllMovies().observe(this, Observer { movies ->
            this.movies = movies
            adapter.showMovies(movies)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.user_state, menu)

        val menuItem = menu?.findItem(R.id.action_search)

        val searchView = menuItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
return true

            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                logOut()
                true
            }
            R.id.action_clear_list -> {
                movieViewModel.clearMovies()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun logOut() {
        getSharedPreferences("state", Context.MODE_PRIVATE)
            .edit()
            .putBoolean("loggedStatus", false)
            .apply()

        val intent = Intent(this, LoginActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        // keeps the main screen from jumping to login screen onBackPressed
        super.onBackPressed()
        finishAffinity()
    }


    private val itemTouChCallBack =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
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


                Snackbar.make(movieListRecyclerView, deletedMovieName, Snackbar.LENGTH_SHORT)
                    .setAction("Undo", View.OnClickListener {
                        adapter.addMovieBack(position)
                        movieViewModel.addMovie(deletedMovie)
                        adapter.notifyItemInserted(position)
                        val deletedMovieName =
                            getString(R.string.re_added_movie, deletedMovie.title)

                        Toast.makeText(applicationContext, deletedMovieName, Toast.LENGTH_SHORT)
                            .show()
                    }).show()

                adapter.deleteMovie(position)
                movieViewModel.deleteMovie(deletedMovie)
                adapter.notifyItemRemoved(position)
            }
        }


}