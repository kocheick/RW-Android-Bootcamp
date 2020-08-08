package com.example.mymovieinfo.ui.main.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovieinfo.R
import com.example.mymovieinfo.model.Movie
import com.example.mymovieinfo.ui.adapters.MoviesListAdapter
import com.example.mymovieinfo.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class SearchMovieFragment : Fragment() {
    lateinit var adapter: MoviesListAdapter
    lateinit var movies: MutableList<Movie>
    lateinit var movieViewModel: MoviesViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        setupAppContent()
    }



    private fun setupAppContent() {
        adapter = MoviesListAdapter()
        movieListRecyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
        movieListRecyclerView.adapter = adapter

        movieViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)



    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.user_state,menu)
        val menuItem = menu.findItem(R.id.action_search)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search(query)
                searchView.clearFocus()

                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun search(query: String?) {
        if (query != null) {
            movieViewModel.searchMovie(query).observe(viewLifecycleOwner, Observer {
               movies = it
                adapter.showMovies(it)
                adapter.favoriteClickListener = { movie, position ->
                    toggleFavorite(movie)
                    adapter.notifyItemChanged(position)
                }
            })
        }
    }

    private fun toggleFavorite(movie: Movie) {
        val wasFavorite = movie.isFavorite
        movie.isFavorite = movie.isFavorite.not()

        if (wasFavorite) {
            println("${movie.title} removed from DB..${movie.isFavorite}")
            movieViewModel.deleteMovie(movie)
        } else{
            println("${movie.title} added to FAVORITES.. saved to DB${movie.isFavorite}")
            movieViewModel.addMovie(movie)
        }
    }

}