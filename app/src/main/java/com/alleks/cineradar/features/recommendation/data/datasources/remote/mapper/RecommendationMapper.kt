package com.alleks.cineradar.features.recommendation.data.datasources.remote.mapper

import com.alleks.cineradar.features.recommendation.data.datasources.remote.model.GenreDto
import com.alleks.cineradar.features.recommendation.data.datasources.remote.model.MovieDetailsResponse
import com.alleks.cineradar.features.recommendation.data.datasources.remote.model.WatchProviderDto
import com.alleks.cineradar.features.recommendation.domain.entities.Genre
import com.alleks.cineradar.features.recommendation.domain.entities.MovieRecommendation
import com.alleks.cineradar.features.recommendation.domain.entities.WatchProvider

fun GenreDto.toDomain(): Genre {
    return Genre(
        id = id,
        name = name
    )
}

fun MovieDetailsResponse.toDomain(watchProviders: List<WatchProvider>): MovieRecommendation {
    return MovieRecommendation(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath?.let { "https://image.tmdb.org/t/p/w500$it" },
        releaseYear = releaseDate?.take(4),
        runtime = runtime,
        genres = genres.map { it.toDomain() },
        voteAverage = voteAverage,
        watchProviders = watchProviders
    )
}

fun WatchProviderDto.toDomain(): WatchProvider {
    return WatchProvider(
        id = providerId,
        name = providerName,
        logoPath = null // TMDB no proporciona logo en este endpoint
    )
}
