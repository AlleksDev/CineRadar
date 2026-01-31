package com.alleks.cineradar.features.recommendation.di

import com.alleks.cineradar.core.di.AppContainer
import com.alleks.cineradar.features.recommendation.data.repositories.RecommendationRepositoryImpl
import com.alleks.cineradar.features.recommendation.domain.repositories.RecommendationRepository
import com.alleks.cineradar.features.recommendation.domain.usecases.GetGenresUseCase
import com.alleks.cineradar.features.recommendation.domain.usecases.GetRecommendationUseCase
import com.alleks.cineradar.features.recommendation.presentation.viewmodels.RecommendationViewModelFactory

class RecommendationModule(
    private val appContainer: AppContainer
) {
    private val recommendationRepository: RecommendationRepository by lazy {
        RecommendationRepositoryImpl(appContainer.cineRadarApi)
    }

    private fun provideGetGenresUseCase(): GetGenresUseCase {
        return GetGenresUseCase(recommendationRepository)
    }

    private fun provideGetRecommendationUseCase(): GetRecommendationUseCase {
        return GetRecommendationUseCase(recommendationRepository)
    }

    fun provideRecommendationViewModelFactory(): RecommendationViewModelFactory {
        return RecommendationViewModelFactory(
            getGenresUseCase = provideGetGenresUseCase(),
            getRecommendationUseCase = provideGetRecommendationUseCase()
        )
    }
}
