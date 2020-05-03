package com.david.hackro.stats.data.datasource.remote.model.rapidapi

import com.david.hackro.stats.domain.model.Help
import com.squareup.moshi.Json

data class HelpResponse(

    @field:Json(name = "alpha2code")
    val alpha2code: String,

    @field:Json(name = "alpha3code")
    val alpha3code: String,

    @field:Json(name = "latitude")
    val latitude: Double,

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "longitude")
    val longitude: Double
)

fun HelpResponse.toDomain() =
    Help(alpha2Code = alpha2code, alpha3Code = alpha3code, latitude = latitude, longitude = longitude, name = name)
