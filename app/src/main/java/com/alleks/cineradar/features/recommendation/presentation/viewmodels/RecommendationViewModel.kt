package com.alleks.cineradar.features.recommendation.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alleks.cineradar.features.recommendation.domain.entities.DurationOption
import com.alleks.cineradar.features.recommendation.domain.entities.Genre
import com.alleks.cineradar.features.recommendation.domain.entities.MovieRecommendation
import com.alleks.cineradar.features.recommendation.domain.entities.RecommendationFilters
import com.alleks.cineradar.features.recommendation.domain.usecases.GetGenresUseCase
import com.alleks.cineradar.features.recommendation.domain.usecases.GetRecommendationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class RecommendationUiState(
    val isLoadingGenres: Boolean = false,
    val isLoadingRecommendation: Boolean = false,
    val genres: List<Genre> = emptyList(),
    val selectedGenreIds: Set<Int> = emptySet(),
    val selectedDuration: DurationOption? = null,
    val keywords: String = "",
    val recommendation: MovieRecommendation? = null,
    val showResult: Boolean = false,
    val error: String? = null
)

class RecommendationViewModel(
    private val getGenresUseCase: GetGenresUseCase,
    private val getRecommendationUseCase: GetRecommendationUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RecommendationUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadGenres()
    }

    private fun loadGenres() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingGenres = true, error = null) }

            getGenresUseCase().fold(
                onSuccess = { genres ->
                    _uiState.update {
                        it.copy(
                            isLoadingGenres = false,
                            genres = genres
                        )
                    }
                },
                onFailure = { exception ->
                    _uiState.update {
                        it.copy(
                            isLoadingGenres = false,
                            error = exception.message ?: "Error al cargar géneros"
                        )
                    }
                }
            )
        }
    }

    fun toggleGenre(genreId: Int) {
        _uiState.update { state ->
            val newSelectedGenres = if (genreId in state.selectedGenreIds) {
                state.selectedGenreIds - genreId
            } else {
                state.selectedGenreIds + genreId
            }
            state.copy(selectedGenreIds = newSelectedGenres)
        }
    }

    fun selectDuration(option: DurationOption?) {
        _uiState.update { it.copy(selectedDuration = option) }
    }

    fun updateKeywords(keywords: String) {
        _uiState.update { it.copy(keywords = keywords) }
    }

    fun getRecommendation() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingRecommendation = true, error = null) }

            val filters = RecommendationFilters(
                selectedGenres = _uiState.value.selectedGenreIds.toList(),
                durationOption = _uiState.value.selectedDuration,
                keywords = _uiState.value.keywords
            )

            getRecommendationUseCase(filters).fold(
                onSuccess = { recommendation ->
                    _uiState.update {
                        it.copy(
                            isLoadingRecommendation = false,
                            recommendation = recommendation,
                            showResult = true
                        )
                    }
                },
                onFailure = { exception ->
                    _uiState.update {
                        it.copy(
                            isLoadingRecommendation = false,
                            error = exception.message ?: "Error al obtener recomendación"
                        )
                    }
                }
            )
        }
    }

    fun backToFilters() {
        _uiState.update {
            it.copy(
                showResult = false,
                recommendation = null
            )
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }

    fun resetFilters() {
        _uiState.update {
            it.copy(
                selectedGenreIds = emptySet(),
                selectedDuration = null,
                keywords = "",
                showResult = false,
                recommendation = null
            )
        }
    }
}
