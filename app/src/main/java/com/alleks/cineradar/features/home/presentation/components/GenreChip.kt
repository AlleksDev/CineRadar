package com.alleks.cineradar.features.home.presentation.components

import androidx.compose.foundation.background
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

@Composable
fun GenreChip(
    genre: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = genre,
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium,
        color = Color.White,
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF3D5A1E))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    )
}
