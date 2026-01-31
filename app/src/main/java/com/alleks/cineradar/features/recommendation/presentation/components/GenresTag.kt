package com.alleks.cineradar.features.recommendation.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alleks.cineradar.features.recommendation.domain.entities.Genre

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GenresTag(
    genres: List<Genre>,
    modifier: Modifier = Modifier
) {
    val genresText = genres.joinToString(", ") { it.name }
    
    Text(
        text = genresText,
        fontSize = 13.sp,
        fontWeight = FontWeight.Medium,
        color = Color(0xFFFFD700),
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFFFD700).copy(alpha = 0.15f))
            .padding(horizontal = 14.dp, vertical = 6.dp)
    )
}
