package com.alleks.cineradar.features.home.domain.repositories

import com.alleks.cineradar.features.home.domain.entities.Movie

interface MoviesRepository {
    suspend fun getMovies(): List<Movie>
}