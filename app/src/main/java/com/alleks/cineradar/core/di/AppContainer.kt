package com.alleks.cineradar.core.di

import android.content.Context
import com.alleks.cineradar.core.network.CineRadarApi
import com.alleks.cineradar.features.home.data.repositories.MoviesRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer(context: Context) {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val cineRadarApi: CineRadarApi by lazy {
        retrofit.create(CineRadarApi::class.java)
    }

    val moviesRepository: MoviesRepositoryImpl by lazy {
        MoviesRepositoryImpl(cineRadarApi)
    }
}