package com.alleks.cineradar.features.recommendation.domain.entities

data class MovieRecommendation(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val releaseYear: String?,
    val runtime: Int?,
    val genres: List<Genre>,
    val voteAverage: Double,
    val watchProviders: List<WatchProvider>
)

data class WatchProvider(
    val id: Int,
    val name: String,
    val logoPath: String?
)
