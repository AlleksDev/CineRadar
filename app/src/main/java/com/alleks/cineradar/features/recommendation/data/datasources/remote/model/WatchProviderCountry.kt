package com.alleks.cineradar.features.recommendation.data.datasources.remote.model

data class WatchProviderCountry(
    val flatrate: List<WatchProviderDto>?,
    val rent: List<WatchProviderDto>?,
    val buy: List<WatchProviderDto>?
)
