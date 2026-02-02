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
        // Construir el string de géneros separados por coma
        val genresQuery = if (filters.selectedGenres.isNotEmpty()) {
            filters.selectedGenres.joinToString(",")
        } else null

        // Obtener películas según filtros
        val moviesResponse = api.discoverMovies(
            genres = genresQuery,
            durationMin = filters.durationOption?.minMinutes,
            durationMax = filters.durationOption?.maxMinutes,
            minRating = null
        )

        // Tomar una película aleatoria de los resultados
        val randomMovie = moviesResponse.results.randomOrNull() ?: return null

        // Obtener detalles completos de la película
        val movieDetails = api.getMovieDetails(randomMovie.id)

        // Obtener proveedores de streaming (para México)
        val watchProvidersResponse = api.getWatchProviders(randomMovie.id)
        val mexicoProviders = watchProvidersResponse.results?.get("MX")
        val providers = mutableListOf<WatchProvider>()
        val addedIds = mutableSetOf<Int>()
        
        // Agregar providers de suscripción (flatrate)
        mexicoProviders?.flatrate?.forEach { provider ->
            if (addedIds.add(provider.providerId)) {
                providers.add(provider.toDomain())
            }
        }
        
        // Si no hay flatrate, agregar de alquiler o compra
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
