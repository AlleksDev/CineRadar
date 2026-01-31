package com.alleks.cineradar.features.recommendation.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alleks.cineradar.features.recommendation.domain.usecases.GetGenresUseCase
import com.alleks.cineradar.features.recommendation.domain.usecases.GetRecommendationUseCase

class RecommendationViewModelFactory(
    private val getGenresUseCase: GetGenresUseCase,
    private val getRecommendationUseCase: GetRecommendationUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecommendationViewModel::class.java)) {
            return RecommendationViewModel(getGenresUseCase, getRecommendationUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
