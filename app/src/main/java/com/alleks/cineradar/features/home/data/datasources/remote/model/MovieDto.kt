package com.alleks.cineradar.features.home.data.datasources.remote.model

data class MoviesResponse(
    val results: List<MovieDto>
)

data class MovieDto(
    val id: Int,
    val title: String,
    val releaseYear: String?,
    val posterPath: String?,
    val rating: Double,
    val genreIds: List<Int>
)
