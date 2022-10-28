package com.imt.fil.filmsapp.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.imt.fil.filmsapp.R
import com.imt.fil.filmsapp.models.Movie
import com.imt.fil.filmsapp.services.MovieService

class MovieListActivity : AppCompatActivity() {
    private lateinit var movieService: MovieService
    private lateinit var moviesListAdapter: MoviesListAdapter
    private var savedMovies: List<Movie>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        movieService = MovieService(this)
        savedMovies = savedInstanceState?.getParcelableArrayList("MOVIES_LIST")
    }

    override fun onStart() {
        super.onStart()
        moviesListAdapter = MoviesListAdapter({
            navigateToDetail(it)
        }, { movie, favorite ->
            movieService.toggleFavorite(movie, favorite)
        })

        movieService.getLatestMovies(success = {
            runOnUiThread {
                moviesListAdapter.dataSet = it
            }
        }, failure = {})
        val moviesList = findViewById<RecyclerView>(R.id.movie_list)
        moviesList.adapter = moviesListAdapter
        moviesList.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
    }

    private fun navigateToDetail(movie: Movie) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra(MovieDetailsActivity.INTENT_PARAM_ID, movie.id)
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList("MOVIES_LIST", ArrayList(moviesListAdapter.dataSet))
        super.onSaveInstanceState(outState)
    }
}