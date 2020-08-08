package com.example.mymovieinfo.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mymovieinfo.R
import com.example.mymovieinfo.model.Movie
import com.example.mymovieinfo.utils.MyAppGlideModule
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class MovieDetailsActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_MOVIE = "EXTRA_MOVIE"

        fun newIntent(context: Context?, movie: Movie): Intent {
            return Intent(context, MovieDetailsActivity::class.java)
                .putExtra(EXTRA_MOVIE, movie)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)

        setupView(movie)
    }

    private fun setupView(movie: Movie?) {
        if (movie != null) {
            movieTitleTextView.text = movie.title
            movie.imageUrl?.original?.let { MyAppGlideModule()
                .loadImage(movieImageView, it) }
            movieSummaryTextView.text = movie.summary
            movieDurationTextView.text = getString(R.string.movie_duration, movie.duration)
            movieReleaseDateTextView.text = movie.releaseDate

            if (movie.isFavorite) {
                movieListFavoriteButton.setBackgroundResource(R.drawable.ic_favoris_red)
            } else {
                movieListFavoriteButton.setBackgroundResource(R.drawable.ic_favorite)
            }
        }
    }
}