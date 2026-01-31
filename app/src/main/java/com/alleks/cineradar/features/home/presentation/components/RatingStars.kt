package com.alleks.cineradar.features.home.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RatingStars(
    rating: Double,
    modifier: Modifier = Modifier,
    maxStars: Int = 5
) {
    // Convertir rating de 0-10 a 0-5
    val normalizedRating = (rating / 2).coerceIn(0.0, 5.0)
    val filledStars = normalizedRating.toInt()
    val hasHalfStar = (normalizedRating - filledStars) >= 0.5

    Row(modifier = modifier) {
        repeat(maxStars) { index ->
            val isFilled = index < filledStars
            val isHalf = index == filledStars && hasHalfStar

            Icon(
                imageVector = if (isFilled || isHalf) Icons.Filled.Star else Icons.Outlined.Star,
                contentDescription = null,
                tint = if (isFilled || isHalf) Color(0xFFFFD700) else Color(0xFF666666),
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
