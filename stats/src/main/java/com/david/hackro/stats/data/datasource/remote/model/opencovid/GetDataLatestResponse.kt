package com.david.hackro.stats.data.datasource.remote.model.opencovid

import com.david.hackro.stats.domain.model.GetDataLatest
import com.squareup.moshi.Json

data class GetDataLatestResponse(

    @field:Json(name = "RegionCode")
    val regionCode: String?,

    @field:Json(name = "CountryName")
    val countryName: String,

    @field:Json(name = "RegionName")
    val regionName: String?,

    @field:Json(name = "Deaths")
    val deaths: Int?,

    @field:Json(name = "Population")
    val population: Int?,

    @field:Json(name = "Latitude")
    val latitude: String?,

    @field:Json(name = "Confirmed")
    val confirmed: Int?,

    @field:Json(name = "CountryCode")
    val countryCode: String,

    @field:Json(name = "Longitude")
    val longitude: String?,

    @field:Json(name = "Date")
    val date: String,

    @field:Json(name = "Key")
    val key: String
)

fun GetDataLatestResponse.toDomain() =
    GetDataLatest(
        regionCode = regionCode,
        countryCode = countryCode,
        regionName = regionName,
        deaths = deaths,
        population = population,
        latitude = latitude,
        confirmed = confirmed,
        countryName = countryName,
        longitude = longitude,
        date = date,
        key = key
    )
