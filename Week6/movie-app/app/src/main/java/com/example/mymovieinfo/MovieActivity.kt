package com.example.mymovieinfo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieActivity : AppCompatActivity() {
    private lateinit var movie: Movie

    companion object {
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"

        fun newIntent(context: Context?, movieId: Int?): Intent {
            return Intent(context, MovieActivity::class.java)
                .putExtra(EXTRA_MOVIE_ID, movieId)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setupView()
    }

    private fun setupView() {
        val movieId = intent.getIntExtra(EXTRA_MOVIE_ID, 0)
        val movieById = MovieStore.getMovieById(movieId)

        if (movieById == null) Toast.makeText(this, "Invalid movie", Toast.LENGTH_SHORT)
            .show() else movie = movieById

        movieTitleTextView.text = movie.title
        movieImageView.setImageResource(movie.image)
        movieSummaryTextView.text = movie.summary
        movieDurationTextView.text = getString(R.string.movie_duration, movie.duration)
        movieReleaseDateTextView.text = movie.releaseDate

    }
}