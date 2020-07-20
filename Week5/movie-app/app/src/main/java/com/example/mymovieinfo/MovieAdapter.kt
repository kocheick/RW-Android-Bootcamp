package com.example.mymovieinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MovieAdapter(private var movies: MutableList<Movie>): RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    lateinit var deletedMovie : Movie
    fun deleteMovie(pos: Int) {
        deletedMovie = movies[pos]
        movies.removeAt(pos)

    }


    fun addMovieBack(position: Int) {
        movies.add(position,deletedMovie)
    }
}