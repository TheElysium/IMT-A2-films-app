package com.imt.fil.filmsapp.datasources

import com.imt.fil.filmsapp.datasources.dto.MovieDto
import com.imt.fil.filmsapp.datasources.dto.MoviesListDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    @GET("movie/{id}")
    fun getMovieById(@Path("id") id: Int): Call<MovieDto>
    @GET("movie/now_playing")
    fun getLatestMovies(): Call<MoviesListDto>
}