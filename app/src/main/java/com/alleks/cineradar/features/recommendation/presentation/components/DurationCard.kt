package com.alleks.cineradar.features.recommendation.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DurationCard(
    topText: String,
    mainText: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val onBackgroundColor = MaterialTheme.colorScheme.onBackground
    
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(
            width = if (isSelected) 2.dp else 1.dp,
            color = if (isSelected) primaryColor else onBackgroundColor.copy(alpha = 0.5f)
        )
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = topText,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = if (isSelected) primaryColor else onBackgroundColor.copy(alpha = 0.7f)
            )
            Text(
                text = mainText,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) primaryColor else onBackgroundColor
            )
            Text(
                text = "min",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = if (isSelected) primaryColor else onBackgroundColor
            )
        }
    }
}
