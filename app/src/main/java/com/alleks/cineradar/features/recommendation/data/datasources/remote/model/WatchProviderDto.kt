package com.alleks.cineradar.features.recommendation.data.datasources.remote.model

import com.google.gson.annotations.SerializedName

data class WatchProviderDto(
    @SerializedName("provider_name")
    val providerName: String,
    @SerializedName("provider_id")
    val providerId: Int
)
