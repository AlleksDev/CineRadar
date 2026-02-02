package com.alleks.cineradar.core.network

import com.alleks.cineradar.features.home.data.datasources.remote.model.MoviesResponse
import com.alleks.cineradar.features.recommendation.data.datasources.remote.model.GenresResponse
import com.alleks.cineradar.features.recommendation.data.datasources.remote.model.MovieDetailsResponse
import com.alleks.cineradar.features.recommendation.data.datasources.remote.model.WatchProvidersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CineRadarApi {
    
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1
    ): MoviesResponse
    
    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("language") language: String = "es-ES"
    ): GenresResponse
    
    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("language") language: String = "es-ES",
        @Query("with_genres") genres: String?,
        @Query("with_runtime.gte") durationMin: Int?,
        @Query("with_runtime.lte") durationMax: Int?,
        @Query("with_original_language") originalLanguage: String = "es",
        @Query("vote_average.gte") minRating: Float?,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): MoviesResponse
    
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "es-ES"
    ): MovieDetailsResponse
    
    @GET("movie/{movie_id}/watch/providers")
    suspend fun getWatchProviders(
        @Path("movie_id") movieId: Int
    ): WatchProvidersResponse
}