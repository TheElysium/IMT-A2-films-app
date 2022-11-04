package com.imt.fil.filmsapp.datasources

import com.imt.fil.filmsapp.datasources.dto.GenreDto
import com.imt.fil.filmsapp.datasources.dto.GenresListDto
import com.imt.fil.filmsapp.datasources.dto.MovieDto
import com.imt.fil.filmsapp.datasources.dto.MoviesListDto
import com.imt.fil.filmsapp.models.Genre
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/{id}")
    fun getMovieById(@Path("id") id: Int): Call<MovieDto>
    @GET("movie/now_playing")
    fun getLatestMovies(): Call<MoviesListDto>
    @GET("search/movie")
    suspend fun getMovieByName(@Query("query") query: String): Response<MoviesListDto>
    @GET("genre/movie/list")
    suspend fun getGenres(): Response<GenresListDto>
}