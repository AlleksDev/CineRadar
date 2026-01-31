package com.alleks.cineradar.features.home.data.datasources.remote.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    val page: Int,
    val results: List<MovieDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

data class MovieDto(
    val id: Int,
    val title: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    val popularity: Double,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("original_language")
    val originalLanguage: String,
    val adult: Boolean
)
