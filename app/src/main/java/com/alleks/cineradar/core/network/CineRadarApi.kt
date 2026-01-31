package com.alleks.cineradar.core.network

import com.alleks.cineradar.features.home.data.datasources.remote.model.MoviesResponse
import retrofit2.http.GET

interface CineRadarApi {
    @GET("movies/popular")
    suspend fun getMovies(): MoviesResponse


}