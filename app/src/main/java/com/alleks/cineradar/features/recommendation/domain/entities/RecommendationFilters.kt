package com.alleks.cineradar.features.recommendation.domain.entities

data class RecommendationFilters(
    val selectedGenres: List<Int> = emptyList(),
    val durationOption: DurationOption? = null,
    val keywords: String = ""
)

enum class DurationOption(val minMinutes: Int?, val maxMinutes: Int?, val label: String) {
    LESS_THAN_90(null, 90, "Menos de 90min"),
    BETWEEN_90_120(90, 120, "Entre 90-120min"),
    MORE_THAN_120(120, null, "Más de 120min")
}
