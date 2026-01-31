package com.alleks.cineradar.features.home.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alleks.cineradar.features.home.presentation.components.ErrorMessage
import com.alleks.cineradar.features.home.presentation.components.LoadingIndicator
import com.alleks.cineradar.features.home.presentation.components.MovieCard
import com.alleks.cineradar.features.home.presentation.components.SectionHeader
import com.alleks.cineradar.features.home.presentation.components.TopBar
import com.alleks.cineradar.features.home.presentation.viewmodels.HomeViewModel

// Mapa de géneros de TMDB (los más comunes)
private val genreMap = mapOf(
    28 to "Acción",
    12 to "Aventura",
    16 to "Animación",
    35 to "Comedia",
    80 to "Crimen",
    99 to "Documental",
    18 to "Drama",
    10751 to "Familia",
    14 to "Fantasía",
    36 to "Historia",
    27 to "Terror",
    10402 to "Música",
    9648 to "Misterio",
    10749 to "Romance",
    878 to "Ciencia ficción",
    10770 to "Película de TV",
    53 to "Suspense",
    10752 to "Bélica",
    37 to "Western"
)

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onRecommendationClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top Bar con logo
        TopBar()

        // Contenido principal
        when {
            uiState.isLoading -> {
                LoadingIndicator()
            }

            uiState.error != null -> {
                ErrorMessage(
                    message = uiState.error!!,
                    onRetry = { viewModel.getMovies() }
                )
            }

            else -> {
                // Header con título y botón de recomendación
                SectionHeader(
                    title = "Catálogo de películas",
                    onRecommendationClick = onRecommendationClick
                )

                // Lista de películas
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(uiState.movies) { movie ->
                        val genreName = movie.genreIds.firstOrNull()?.let { genreId ->
                            genreMap[genreId] ?: "Sin género"
                        } ?: "Sin género"

                        MovieCard(
                            movie = movie,
                            genreName = genreName
                        )
                    }
                }
            }
        }
    }
}
