package com.example.mymovieinfo.ui.adapters

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieinfo.R
import com.example.mymovieinfo.model.Movie
import com.example.mymovieinfo.ui.main.MovieDetailsActivity
import com.example.mymovieinfo.utils.MyAppGlideModule
import kotlinx.android.synthetic.main.movie_list_item.view.*
import kotlinx.coroutines.InternalCoroutinesApi

@RequiresApi(Build.VERSION_CODES.M)
@InternalCoroutinesApi
class MoviesListViewHolder(private val containerView: View) :
    RecyclerView.ViewHolder(containerView), View.OnClickListener {
    private lateinit var movie: Movie

    init {
        containerView.setOnClickListener(this)
    }

    fun bind(movie: Movie) = with(containerView) {
        movieTitleTextView.text = movie.title
        movie.imageUrl?.medium?.let { imgURL -> MyAppGlideModule()
            .loadImage(movieImageView, imgURL) }
         movieSummaryTextView.text = movie.summary
        movieDurationTextView.text = itemView.context.getString(R.string.movie_duration, movie.duration)
        movieReleaseDateTextView.text = movie.releaseDate?.substringBefore("-")

        this@MoviesListViewHolder.movie = movie

    }

    override fun onClick(view: View) {
        val context = view.context
        val intent = MovieDetailsActivity.newIntent(
                context,
                movie
            )
        context.startActivity(intent)
    }



}