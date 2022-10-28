package com.imt.fil.filmsapp.entities.transformers

import com.imt.fil.filmsapp.datasources.dto.MovieDto
import com.imt.fil.filmsapp.datasources.dto.MoviesListDto
import com.imt.fil.filmsapp.models.Movie
import java.time.LocalDate

fun moviesListDtoToMoviesList(latestMoviesDto: MoviesListDto): List<Movie> {
    val movies = latestMoviesDto.results.map {
        Movie(
            id = it.id,
            posterUrl = it.poster_path,
            backdropUrl = it.backdrop_path,
            title = it.title,
            averageVote = it.vote_average,
            votesNumber = it.vote_count,
            overview = it.overview,
            releaseDate = LocalDate.parse(it.release_date),
        )
    }

    return movies
}

fun movieDtoFromMovie(movieDto: MovieDto): Movie {
    return Movie(
        id = movieDto.id,
        posterUrl = movieDto.poster_path,
        backdropUrl = movieDto.backdrop_path,
        title = movieDto.title,
        averageVote = movieDto.vote_average,
        votesNumber = movieDto.vote_count,
        overview = movieDto.overview,
        releaseDate = LocalDate.parse(movieDto.release_date),
    )
}