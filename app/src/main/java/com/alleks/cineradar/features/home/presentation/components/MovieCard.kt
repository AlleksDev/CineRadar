package com.alleks.cineradar.features.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.alleks.cineradar.features.home.domain.entities.Movie

@Composable
fun MovieCard(
    movie: Movie,
    genreName: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2A3D1A)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Poster de la película
            Box(
                modifier = Modifier
                    .size(width = 80.dp, height = 100.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF1A2E0A))
            ) {
                if (movie.posterPath != null) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w200${movie.posterPath}",
                        contentDescription = movie.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(width = 80.dp, height = 100.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Información de la película
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                // Título y año
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = movie.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    if (movie.releaseYear != null) {
                        Text(
                            text = " (${movie.releaseYear})",
                            fontSize = 12.sp,
                            color = Color(0xFFAAAAAA)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Género
                GenreChip(genre = genreName)

                Spacer(modifier = Modifier.height(8.dp))

                // Rating con estrellas
                RatingStars(rating = movie.rating)
            }
        }
    }
}
