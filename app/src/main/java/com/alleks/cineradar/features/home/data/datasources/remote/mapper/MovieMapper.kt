package com.alleks.cineradar.features.home.data.datasources.remote.mapper

import com.alleks.cineradar.features.home.data.datasources.remote.model.MovieDto
import com.alleks.cineradar.features.home.domain.entities.Movie

fun MovieDto.toDomain(): Movie {
    // Extraer el año de release_date (formato: "2024-05-15")
    val year = releaseDate?.takeIf { it.length >= 4 }?.substring(0, 4)
    
    return Movie(
        id = this.id,
        title = this.title,
        releaseYear = year,
        posterPath = this.posterPath,
        rating = this.voteAverage,
        genreIds = this.genreIds
    )
}