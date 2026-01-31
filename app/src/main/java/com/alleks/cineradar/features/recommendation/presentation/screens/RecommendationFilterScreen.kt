package com.alleks.cineradar.features.recommendation.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alleks.cineradar.features.home.presentation.components.TopBar
import com.alleks.cineradar.features.recommendation.domain.entities.DurationOption
import com.alleks.cineradar.features.recommendation.presentation.components.DurationCard
import com.alleks.cineradar.features.recommendation.presentation.components.KeywordsTextField
import com.alleks.cineradar.features.recommendation.presentation.components.SelectableGenreChip
import com.alleks.cineradar.features.recommendation.presentation.viewmodels.RecommendationViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RecommendationFilterScreen(
    viewModel: RecommendationViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val backgroundColor = Color(0xFF1A2E0A)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopBar()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                // Sección: ¿Qué quieres ver?
                Text(
                    text = "¿Qué quieres ver?",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (uiState.isLoadingGenres) {
                    CircularProgressIndicator(
                        color = Color(0xFFFFD700),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                } else {
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        uiState.genres.forEach { genre ->
                            SelectableGenreChip(
                                genre = genre.name,
                                isSelected = genre.id in uiState.selectedGenreIds,
                                onToggle = { viewModel.toggleGenre(genre.id) }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Sección: ¿Cuánto tiempo tienes?
                Text(
                    text = "¿Cuánto tiempo tienes?",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    DurationCard(
                        topText = "Menos de",
                        mainText = "90min",
                        isSelected = uiState.selectedDuration == DurationOption.LESS_THAN_90,
                        onClick = { 
                            viewModel.selectDuration(
                                if (uiState.selectedDuration == DurationOption.LESS_THAN_90) null
                                else DurationOption.LESS_THAN_90
                            )
                        },
                        modifier = Modifier.weight(1f)
                    )
                    DurationCard(
                        topText = "Entre",
                        mainText = "90-120min",
                        isSelected = uiState.selectedDuration == DurationOption.BETWEEN_90_120,
                        onClick = { 
                            viewModel.selectDuration(
                                if (uiState.selectedDuration == DurationOption.BETWEEN_90_120) null
                                else DurationOption.BETWEEN_90_120
                            )
                        },
                        modifier = Modifier.weight(1f)
                    )
                    DurationCard(
                        topText = "Más de",
                        mainText = "120min",
                        isSelected = uiState.selectedDuration == DurationOption.MORE_THAN_120,
                        onClick = { 
                            viewModel.selectDuration(
                                if (uiState.selectedDuration == DurationOption.MORE_THAN_120) null
                                else DurationOption.MORE_THAN_120
                            )
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Sección: Detalles extra
                Text(
                    text = "¿Quieres añadir detalles extra?",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(16.dp))

                KeywordsTextField(
                    value = uiState.keywords,
                    onValueChange = { viewModel.updateKeywords(it) }
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Botón de búsqueda
                Button(
                    onClick = { viewModel.getRecommendation() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    enabled = !uiState.isLoadingRecommendation,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFD700),
                        contentColor = Color(0xFF1A2E0A)
                    )
                ) {
                    if (uiState.isLoadingRecommendation) {
                        CircularProgressIndicator(
                            color = Color(0xFF1A2E0A),
                            modifier = Modifier.padding(4.dp)
                        )
                    } else {
                        Text(
                            text = "Buscar recomendación",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // Mostrar error si existe
                uiState.error?.let { error ->
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = error,
                        fontSize = 14.sp,
                        color = Color(0xFFFF6B6B),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
