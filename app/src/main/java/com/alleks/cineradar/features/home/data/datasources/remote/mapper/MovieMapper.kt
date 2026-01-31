package com.alleks.cineradar.features.home.data.datasources.remote.mapper

import com.alleks.cineradar.features.home.data.datasources.remote.model.MovieDto
import com.alleks.cineradar.features.home.domain.entities.Movie

fun MovieDto.toDomain(): Movie {
    return Movie(
        title = this.title,
        releaseYear = this.releaseYear,
        posterPath = this.posterPath,
        rating = this.rating,
        genreIds = this.genreIds,
        id = this.id
    )
}