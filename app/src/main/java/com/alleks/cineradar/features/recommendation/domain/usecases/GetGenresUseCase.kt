package com.alleks.cineradar.features.recommendation.domain.usecases

import com.alleks.cineradar.features.recommendation.domain.entities.Genre
import com.alleks.cineradar.features.recommendation.domain.repositories.RecommendationRepository

class GetGenresUseCase(
    private val repository: RecommendationRepository
) {
    suspend operator fun invoke(): Result<List<Genre>> {
        return try {
            val genres = repository.getGenres()
            if (genres.isEmpty()) {
                Result.failure(Exception("No se encontraron géneros"))
            } else {
                Result.success(genres)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
