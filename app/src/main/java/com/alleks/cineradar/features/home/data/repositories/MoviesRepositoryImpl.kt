package com.alleks.cineradar.features.home.data.repositories

import com.alleks.cineradar.core.network.CineRadarApi
import com.alleks.cineradar.features.home.data.datasources.remote.mapper.toDomain
import com.alleks.cineradar.features.home.domain.repositories.MoviesRepository
import com.alleks.cineradar.features.home.domain.entities.Movie

class MoviesRepositoryImpl(
    private val  api: CineRadarApi
): MoviesRepository {
    override suspend fun getMovies(): List<Movie> {
        val response = api.getMovies()
        return response.results.map { it.toDomain() }
    }

}