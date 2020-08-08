package com.example.mymovieinfo.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieinfo.R
import com.example.mymovieinfo.model.Movie
import com.example.mymovieinfo.ui.adapters.MoviesListAdapter
import com.example.mymovieinfo.viewmodel.FavoriteMoviesViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class FavoriteMoviesFragment : Fragment() {

    lateinit var adapter: MoviesListAdapter
    private lateinit var movieViewModel: FavoriteMoviesViewModel
    lateinit var movies: MutableList<Movie>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUI()

    }

    private fun initUI() {
        adapter = MoviesListAdapter()
        movieListRecyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
        movieListRecyclerView.adapter = adapter
        ItemTouchHelper(itemTouChCallBack).attachToRecyclerView(movieListRecyclerView)

        movieViewModel = ViewModelProvider(this).get(FavoriteMoviesViewModel::class.java)

        movieViewModel.getAllMovies().observe(viewLifecycleOwner, Observer { movies ->
            this.movies = movies
            adapter.showMovies(movies)

        })
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
                    movieViewModel.addMovie(deletedMovie)
                    adapter.notifyItemInserted(position)
                    val deletedMovieName = getString(R.string.re_added_movie, deletedMovie.title)
                    Toast.makeText(
                        activity?.applicationContext,
                        deletedMovieName,
                        Toast.LENGTH_SHORT
                    ).show()
                }).show()
            adapter.deleteMovie(position)
            movieViewModel.deleteMovie(deletedMovie)
            adapter.notifyItemRemoved(position)
        }
    }


}