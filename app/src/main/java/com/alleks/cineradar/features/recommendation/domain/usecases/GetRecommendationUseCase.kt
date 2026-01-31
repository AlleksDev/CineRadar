package com.alleks.cineradar.features.recommendation.domain.usecases

import com.alleks.cineradar.features.recommendation.domain.entities.MovieRecommendation
import com.alleks.cineradar.features.recommendation.domain.entities.RecommendationFilters
import com.alleks.cineradar.features.recommendation.domain.repositories.RecommendationRepository

class GetRecommendationUseCase(
    private val repository: RecommendationRepository
) {
    suspend operator fun invoke(filters: RecommendationFilters): Result<MovieRecommendation> {
        return try {
            val recommendation = repository.getRecommendation(filters)
            if (recommendation != null) {
                Result.success(recommendation)
            } else {
                Result.failure(Exception("No se encontró ninguna película con esos filtros"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
