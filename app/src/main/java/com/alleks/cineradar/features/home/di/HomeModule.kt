package com.alleks.cineradar.features.home.di

import com.alleks.cineradar.core.di.AppContainer
import com.alleks.cineradar.features.home.domain.usecases.GetMoviesUseCase
import com.alleks.cineradar.features.home.presentation.viewmodels.HomeViewModelFactory

class HomeModule(
    private val appContainer: AppContainer
) {
    private fun provideGetMoviesUseCase(): GetMoviesUseCase {
        return GetMoviesUseCase(appContainer.moviesRepository)
    }

    fun provideMoviesViewModelFactory(): HomeViewModelFactory {
        return HomeViewModelFactory(
            getMoviesUseCase = provideGetMoviesUseCase()
        )
    }
}