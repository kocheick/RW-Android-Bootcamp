package com.example.mymovieinfo

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MovieViewHolder(private val containerView: View): RecyclerView.ViewHolder(containerView), View.OnClickListener {

    private lateinit var movie: Movie

    init {
        containerView.setOnClickListener(this)
    }

    fun bind(movie: Movie) = with(containerView){
        this@MovieViewHolder.movie = movie
        movieTitleTextView.text = movie.title
        movieImageView.setImageResource(movie.image)
        movieSummaryTextView.text = movie.summary
        movieDurationTextView.text = itemView.context.getString(R.string.movie_duration, movie.duration)
        movieReleaseDateTextView.text = movie.releaseDate?.substringAfterLast(".")
    }

    override fun onClick(view: View) {
        val context = view.context
        val intent  = MovieActivity.newIntent(context,movie.id )
        context.startActivity(intent)

    }


}