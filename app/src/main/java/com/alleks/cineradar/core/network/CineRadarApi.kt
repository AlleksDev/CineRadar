package com.alleks.cineradar.core.network

import com.alleks.cineradar.features.home.data.datasources.remote.model.MoviesResponse
import retrofit2.http.GET
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
        @retrofit2.http.Path("movie_id") movieId: Int,
        @Query("language") language: String = "es-ES"
    ): MovieDetailsResponse
    
    @GET("movie/{movie_id}/watch/providers")
    suspend fun getWatchProviders(
        @retrofit2.http.Path("movie_id") movieId: Int
    ): WatchProvidersResponse
}

// Modelos adicionales para TMDB
data class GenresResponse(
    val genres: List<GenreDto>
)

data class GenreDto(
    val id: Int,
    val name: String
)

data class MovieDetailsResponse(
    val id: Int,
    val title: String,
    val overview: String,
    @com.google.gson.annotations.SerializedName("poster_path")
    val posterPath: String?,
    @com.google.gson.annotations.SerializedName("release_date")
    val releaseDate: String?,
    @com.google.gson.annotations.SerializedName("vote_average")
    val voteAverage: Double,
    val runtime: Int?,
    val genres: List<GenreDto>
)

data class WatchProvidersResponse(
    val results: Map<String, WatchProviderCountry>?
)

data class WatchProviderCountry(
    val flatrate: List<WatchProvider>?,
    val rent: List<WatchProvider>?,
    val buy: List<WatchProvider>?
)

data class WatchProvider(
    @com.google.gson.annotations.SerializedName("provider_name")
    val providerName: String,
    @com.google.gson.annotations.SerializedName("provider_id")
    val providerId: Int
)