package com.david.hackro.stats.data.datasource.remote.model

import com.david.hackro.stats.domain.model.DataByStatusItem
import com.squareup.moshi.Json

data class DataByStatusResponse(

    @field:Json(name = "countryRegion")
    val countryRegion: String,

    @field:Json(name = "active")
    val active: Int,

    @field:Json(name = "confirmed")
    val confirmed: Int,

    @field:Json(name = "provinceState")
    val provinceState: String?,

    @field:Json(name = "long")
    val lng: Float?,

    @field:Json(name = "incidentRate")
    val incidentRate: Float?,

    @field:Json(name = "uid")
    val uid: Int,

    @field:Json(name = "recovered")
    val recovered: Int,

    @field:Json(name = "lastUpdate")
    val lastUpdate: Long,

    @field:Json(name = "combinedKey")
    val combinedKey: String,

    @field:Json(name = "iso2")
    val iso2: String?,

    @field:Json(name = "lat")
    val lat: Float?,

    @field:Json(name = "deaths")
    val deaths: Int,

    @field:Json(name = "iso3")
    val iso3: String?
)

fun DataByStatusResponse.toModel() = DataByStatusItem(
    countryRegion = countryRegion,
    lastUpdate = lastUpdate,
    deaths = deaths,
    confirmed = confirmed,
    recovered = recovered,
    active = active,
    combinedKey = combinedKey,
    incidentRate = incidentRate,
    iso2 = iso2,
    iso3 = iso3,
    lat = lat,
    lng = lng,
    provinceState = provinceState,
    uid = uid
)
