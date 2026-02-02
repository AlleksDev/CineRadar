package com.alleks.cineradar.features.recommendation.data.datasources.remote.model

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    val id: Int,
    val title: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("vote_average")
    val voteAverage: Double,
    val runtime: Int?,
    val genres: List<GenreDto>
)
