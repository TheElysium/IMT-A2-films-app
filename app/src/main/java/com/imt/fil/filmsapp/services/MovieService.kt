package com.imt.fil.filmsapp.services

import android.content.Context
import com.imt.fil.filmsapp.CustomApplication
import com.imt.fil.filmsapp.datasources.NetworkDataSource
import com.imt.fil.filmsapp.datasources.dao.MovieDao
import com.imt.fil.filmsapp.models.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieService(context: Context) {
    private val movieDao: MovieDao
    private val networkDataSource = NetworkDataSource()

    init {
        val db = (context.applicationContext as CustomApplication).getDb()

        movieDao = db.movieDao()
    }

    fun getMovieDetail(id: Int, success: (movie: Movie?) -> Unit, failure: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            networkDataSource.getMovieById(id, success = { movie ->
                val movieDb = movieDao.getById(id)
                movieDb?.let {
                    movie.favorite = true
                }
                success(movie)
            }, failure = {
                failure()
            })
        }
    }

    fun getLatestMovies(success: (movies: List<Movie>) -> Unit, failure: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            networkDataSource.getLatestMovies(success = { movies ->
                val moviesDb = movieDao.getAll()
                movies.map { movie ->
                    movie.favorite = moviesDb.any { it.id == movie.id }
                }
                success(movies)
            }, failure = {
                failure()
            })
        }
    }

    fun toggleFavorite(movie: Movie, favorite: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            if (favorite)
                movieDao.addFavorite(movie)
            else
                movieDao.removeFavorite(movie)
        }
    }
}