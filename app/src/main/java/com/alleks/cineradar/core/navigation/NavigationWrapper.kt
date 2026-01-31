package com.alleks.cineradar.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alleks.cineradar.core.di.AppContainer
import com.alleks.cineradar.features.home.di.HomeModule
import com.alleks.cineradar.features.home.presentation.screens.HomeScreen
import com.alleks.cineradar.features.home.presentation.viewmodels.HomeViewModel
import com.alleks.cineradar.features.recommendation.di.RecommendationModule
import com.alleks.cineradar.features.recommendation.presentation.screens.RecommendationScreen
import com.alleks.cineradar.features.recommendation.presentation.viewmodels.RecommendationViewModel

@Composable
fun NavigationWrapper(
    appContainer: AppContainer
) {
    val navController = rememberNavController()

    // Módulos de inyección de dependencias
    val homeModule = HomeModule(appContainer)
    val recommendationModule = RecommendationModule(appContainer)

    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        // Pantalla Home
        composable<Home> {
            val homeViewModel: HomeViewModel = viewModel(
                factory = homeModule.provideMoviesViewModelFactory()
            )
            
            HomeScreen(
                viewModel = homeViewModel,
                onRecommendationClick = {
                    navController.navigate(Recommendation)
                }
            )
        }

        // Pantalla Recommendation
        composable<Recommendation> {
            val recommendationViewModel: RecommendationViewModel = viewModel(
                factory = recommendationModule.provideRecommendationViewModelFactory()
            )
            
            RecommendationScreen(
                viewModel = recommendationViewModel
            )
        }
    }
}
