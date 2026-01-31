package com.alleks.cineradar.features.recommendation.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KeywordsTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 120.dp),
        placeholder = {
            Text(
                text = "Escribe palabras clave separadas por \",\"\nEj. Basada en hechos reales, huérfano, hermanos",
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.5f),
                lineHeight = 20.sp
            )
        },
        textStyle = TextStyle(
            fontSize = 14.sp,
            color = Color.White
        ),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFFFFD700),
            unfocusedBorderColor = Color.White.copy(alpha = 0.3f),
            cursorColor = Color(0xFFFFD700),
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        )
    )
}
