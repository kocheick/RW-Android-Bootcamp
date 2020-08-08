package com.example.mymovieinfo.ui.adapters

import android.os.Build
import android.view.LayoutInflater.from
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieinfo.R
import com.example.mymovieinfo.model.Movie
import kotlinx.android.synthetic.main.movie_list_item.view.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class MoviesListAdapter() :RecyclerView.Adapter<MoviesListViewHolder>() {
    lateinit var favoriteClickListener: ((Movie, Int) -> Unit)
    var movies = mutableListOf<Movie>()
    private val differCallback = object : DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem==newItem
         }
    }

    val differ = AsyncListDiffer(this,differCallback)

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListViewHolder {
        val view = from(parent.context)
            .inflate(R.layout.movie_list_item, parent, false)
        return MoviesListViewHolder(view)

    }


    override fun getItemCount(): Int {
        return movies.size
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: MoviesListViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.movieListFavoriteButton.setOnClickListener {
            favoriteClickListener(movie, holder.layoutPosition)

        }
        val favoriteBtn = holder.itemView.movieListFavoriteButton
        if (movie.isFavorite) {
            favoriteBtn.setBackgroundResource(R.drawable.ic_favoris_red)
        } else {
            favoriteBtn.setBackgroundResource(R.drawable.ic_favorite)
        }
    }

    fun showMovies(movies: MutableList<Movie>) {

        this.movies.clear()
        this.movies = movies
        notifyDataSetChanged()
    }

    private lateinit var deletedMovie: Movie
    // delete or add-back movie methods from adapter
    fun deleteMovie(pos: Int) {
        deletedMovie = movies[pos]
        movies.removeAt(pos)
    }
    fun addMovieBack(position: Int) {
        movies.add(position, deletedMovie)
    }

}