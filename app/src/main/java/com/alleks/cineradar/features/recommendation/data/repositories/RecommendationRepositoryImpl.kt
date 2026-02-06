package com.alleks.cineradar.features.recommendation.data.repositories

import com.alleks.cineradar.core.network.CineRadarApi
import com.alleks.cineradar.features.recommendation.data.datasources.remote.mapper.toDomain
import com.alleks.cineradar.features.recommendation.domain.entities.Genre
import com.alleks.cineradar.features.recommendation.domain.entities.MovieRecommendation
import com.alleks.cineradar.features.recommendation.domain.entities.RecommendationFilters
import com.alleks.cineradar.features.recommendation.domain.entities.WatchProvider
import com.alleks.cineradar.features.recommendation.domain.repositories.RecommendationRepository

class RecommendationRepositoryImpl(
    private val api: CineRadarApi
) : RecommendationRepository {

    override suspend fun getGenres(): List<Genre> {
        val response = api.getGenres()
        return response.genres.map { it.toDomain() }
    }

    override suspend fun getRecommendation(filters: RecommendationFilters): MovieRecommendation? {
        val genresQuery = if (filters.selectedGenres.isNotEmpty()) {
            filters.selectedGenres.joinToString(",")
        } else null

        val moviesResponse = api.discoverMovies(
            genres = genresQuery,
            durationMin = filters.durationOption?.minMinutes,
            durationMax = filters.durationOption?.maxMinutes,
            minRating = null
        )

        val randomMovie = moviesResponse.results.randomOrNull() ?: return null

        val movieDetails = api.getMovieDetails(randomMovie.id)

        val watchProvidersResponse = api.getWatchProviders(randomMovie.id)
        val mexicoProviders = watchProvidersResponse.results?.get("MX")
        val providers = mutableListOf<WatchProvider>()
        val addedIds = mutableSetOf<Int>()

        mexicoProviders?.flatrate?.forEach { provider ->
            if (addedIds.add(provider.providerId)) {
                providers.add(provider.toDomain())
            }
        }

        if (providers.isEmpty()) {
            mexicoProviders?.rent?.forEach { provider ->
                if (addedIds.add(provider.providerId)) {
                    providers.add(provider.toDomain())
                }
            }
            mexicoProviders?.buy?.forEach { provider ->
                if (addedIds.add(provider.providerId)) {
                    providers.add(provider.toDomain())
                }
            }
        }
        return movieDetails.toDomain(providers)
    }
}
