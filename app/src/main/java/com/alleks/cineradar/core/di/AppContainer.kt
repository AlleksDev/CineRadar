package com.alleks.cineradar.core.di

import android.content.Context
import com.alleks.cineradar.BuildConfig
import com.alleks.cineradar.core.network.CineRadarApi
import com.alleks.cineradar.features.home.data.repositories.MoviesRepositoryImpl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer(context: Context) {
    
    companion object {
        private const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
        private val TMDB_API_KEY = BuildConfig.TMDB_API_KEY
    }
    
    // Interceptor para agregar el token de autorización a todas las peticiones
    private val authInterceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $TMDB_API_KEY")
            .header("accept", "application/json")
            .build()
        chain.proceed(newRequest)
    }
    
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()
    
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(TMDB_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val cineRadarApi: CineRadarApi by lazy {
        retrofit.create(CineRadarApi::class.java)
    }

    val moviesRepository: MoviesRepositoryImpl by lazy {
        MoviesRepositoryImpl(cineRadarApi)
    }
}