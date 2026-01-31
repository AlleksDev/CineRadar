package com.alleks.cineradar.features.recommendation.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.alleks.cineradar.features.recommendation.presentation.viewmodels.RecommendationViewModel

@Composable
fun RecommendationScreen(
    viewModel: RecommendationViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.showResult && uiState.recommendation != null) {
        RecommendationResultScreen(
            movie = uiState.recommendation!!,
            onBackToFilters = { viewModel.backToFilters() },
            onSearchAnother = { viewModel.getRecommendation() },
            modifier = modifier
        )
    } else {
        RecommendationFilterScreen(
            viewModel = viewModel,
            modifier = modifier
        )
    }
}
