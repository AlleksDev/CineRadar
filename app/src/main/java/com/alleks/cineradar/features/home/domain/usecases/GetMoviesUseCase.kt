package com.alleks.cineradar.features.home.domain.usecases

import com.alleks.cineradar.features.home.domain.entities.Movie
import com.alleks.cineradar.features.home.domain.repositories.MoviesRepository

class GetMoviesUseCase(
    private val repository: MoviesRepository
) {
    suspend operator fun invoke(): Result<List<Movie>> {
        return try {
            val movies = repository.getMovies()
            if (movies.isEmpty()) {
                Result.failure(Exception("No movies found"))
            } else {
                Result.success(movies)
            }
        } catch (e: Exception){
            Result.failure(e)
        }
    }

}