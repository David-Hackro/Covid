package com.david.hackro.stats.data.datasource.remote.model

import com.david.hackro.stats.domain.model.Report
import com.squareup.moshi.Json

data class ReportResponse(

    @field:Json(name = "date")
    val date: String,

    @field:Json(name = "country")
    val country: String,

    @field:Json(name = "provinces")
    val provinces: List<ProvincesItem>,

    @field:Json(name = "latitude")
    val latitude: Double,

    @field:Json(name = "longitude")
    val longitude: Double
)

data class ProvincesItem(

    @field:Json(name = "recovered")
    val recovered: Int,

    @field:Json(name = "province")
    val province: String,

    @field:Json(name = "active")
    val active: Int,

    @field:Json(name = "confirmed")
    val confirmed: Int,

    @field:Json(name = "deaths")
    val deaths: Int
)

fun ReportResponse.toDomain() =
    Report(date = date, country = country, provinces = provinces.map { it.toDomain() }, latitude = latitude, longitude = longitude)

private fun ProvincesItem.toDomain() =
    com.david.hackro.stats.domain.model.ProvincesItem(
        recovered = recovered,
        province = province,
        active = active,
        confirmed = confirmed,
        deaths = deaths
    )
