package com.imt.fil.filmsapp.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.imt.fil.filmsapp.R
import com.imt.fil.filmsapp.models.Movie
import com.imt.fil.filmsapp.services.MovieService

class SearchMovieActivity : AppCompatActivity() {
    private lateinit var movieService: MovieService
    private var savedMovies: List<Movie>? = null
    private lateinit var searchListAdapter: SearchListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)
        movieService = MovieService(this)
        savedMovies = savedInstanceState?.getParcelableArrayList("MOVIES_LIST")
    }

    override fun onStart() {
        super.onStart();
        searchListAdapter = SearchListAdapter {
            navigateToDetail(it)
        };
        val searchMovie = findViewById<SearchView>(R.id.search)

        searchMovie.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                Log.d("searchMovie", newText) // REMOVE
                movieService.getMoviesByName(newText, success = {
                    runOnUiThread {
                        searchListAdapter.dataSet = it
                    }
                }, failure = {})
                return false;
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                Log.d("searchMovie", query) // REMOVE
                movieService.getMoviesByName(query, success = {
                    runOnUiThread {
                        searchListAdapter.dataSet = it
                    }
                }, failure = {})
                return false;
            }

        })
        val searchMoviesList = findViewById<RecyclerView>(R.id.searchList)
        searchMoviesList.adapter = searchListAdapter
        searchMoviesList.layoutManager = LinearLayoutManager(this)

    }

    private fun navigateToDetail(movie: Movie) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra(MovieDetailsActivity.INTENT_PARAM_ID, movie.id)
        startActivity(intent)
    }
}