package com.alleks.cineradar.features.recommendation.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SelectableGenreChip(
    genre: String,
    isSelected: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilterChip(
        selected = isSelected,
        onClick = onToggle,
        label = {
            Text(
                text = genre,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        },
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = FilterChipDefaults.filterChipColors(
            containerColor = Color.Transparent,
            labelColor = Color.White,
            selectedContainerColor = Color.Transparent,
            selectedLabelColor = Color(0xFFFFD700)
        ),
        border = FilterChipDefaults.filterChipBorder(
            enabled = true,
            selected = isSelected,
            borderColor = Color.White.copy(alpha = 0.5f),
            selectedBorderColor = Color(0xFFFFD700),
            borderWidth = 1.dp,
            selectedBorderWidth = 2.dp
        )
    )
}
