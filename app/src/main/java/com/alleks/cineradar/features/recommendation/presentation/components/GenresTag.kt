package com.alleks.cineradar.features.recommendation.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alleks.cineradar.features.recommendation.domain.entities.Genre

@Composable
fun GenresTag(
    genres: List<Genre>,
    modifier: Modifier = Modifier
) {
    val genresText = genres.joinToString(", ") { it.name }
    val primaryColor = MaterialTheme.colorScheme.primary
    
    Text(
        text = genresText,
        fontSize = 13.sp,
        fontWeight = FontWeight.Medium,
        color = primaryColor,
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(primaryColor.copy(alpha = 0.15f))
            .padding(horizontal = 14.dp, vertical = 6.dp)
    )
}
