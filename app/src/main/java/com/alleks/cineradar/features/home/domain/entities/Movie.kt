package com.alleks.cineradar.features.home.domain.entities

data class Movie(
    val id: Int,
    val title: String,
    val releaseYear: String?,
    val posterPath: String?,
    val rating: Double,
    val genreIds: List<Int>
)
