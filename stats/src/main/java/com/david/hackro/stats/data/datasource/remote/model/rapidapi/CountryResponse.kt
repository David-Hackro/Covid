package com.david.hackro.stats.data.datasource.remote.model.rapidapi

import com.david.hackro.stats.domain.model.Country
import com.squareup.moshi.Json

data class CountryResponse(

    @field:Json(name = "country")
    val country: String,

    @field:Json(name = "recovered")
    val recovered: Int,

    @field:Json(name = "critical")
    val critical: Int,

    @field:Json(name = "latitude")
    val latitude: Double,

    @field:Json(name = "lastUpdate")
    val lastUpdate: String,

    @field:Json(name = "lastChange")
    val lastChange: String,

    @field:Json(name = "confirmed")
    val confirmed: Int,

    @field:Json(name = "deaths")
    val deaths: Int,

    @field:Json(name = "longitude")
    val longitude: Double
)

fun CountryResponse.toDomain() = Country(
    country = country, recovered = recovered, critical = critical, latitude = latitude, lastUpdate = lastUpdate,
    lastChange = lastChange, confirmed = confirmed, deaths = deaths, longitude = longitude
)
