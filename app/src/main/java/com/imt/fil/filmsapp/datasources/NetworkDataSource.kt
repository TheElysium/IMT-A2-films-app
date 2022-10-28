package com.imt.fil.filmsapp.datasources

import android.util.Log
import androidx.annotation.WorkerThread
import com.imt.fil.filmsapp.entities.transformers.movieDtoFromMovie
import com.imt.fil.filmsapp.entities.transformers.moviesListDtoToMoviesList
import com.imt.fil.filmsapp.models.Movie


class NetworkDataSource {
    @WorkerThread
    suspend fun getLatestMovies(success: (movies: List<Movie>) -> Unit, failure: () -> Unit) {
        try {
            val response = ApiClient.movieService.getLatestMovies().execute()

            if (response.isSuccessful && response.body() != null) {
                val content = response.body()
                content?.let {
                    success(moviesListDtoToMoviesList(it))
                }
            } else
                failure()
        } catch (e: Exception) {
            Log.d("NetworkDataSource", e.toString())
            failure()
        }
    }


    @WorkerThread
    suspend fun getMovieById(id: Int, success: (movie: Movie) -> Unit, failure: () -> Unit) {
        try {
            val response = ApiClient.movieService.getMovieById(id).execute()

            if (response.isSuccessful && response.body() != null) {
                val content = response.body()
                content?.let {
                    success(movieDtoFromMovie(it))
                }
            } else
                failure()
        } catch (e: Exception) {
            Log.d("NetworkDataSource", e.toString())
            failure()
        }
    }
}