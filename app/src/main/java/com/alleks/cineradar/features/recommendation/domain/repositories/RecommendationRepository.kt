package com.alleks.cineradar.features.recommendation.domain.repositories

import com.alleks.cineradar.features.recommendation.domain.entities.Genre
import com.alleks.cineradar.features.recommendation.domain.entities.MovieRecommendation
import com.alleks.cineradar.features.recommendation.domain.entities.RecommendationFilters

interface RecommendationRepository {
    suspend fun getGenres(): List<Genre>
    suspend fun getRecommendation(filters: RecommendationFilters): MovieRecommendation?
}
