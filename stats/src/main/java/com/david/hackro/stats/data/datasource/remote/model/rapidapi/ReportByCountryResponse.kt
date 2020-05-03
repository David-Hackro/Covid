package com.david.hackro.stats.data.datasource.remote.model.rapidapi

import com.david.hackro.stats.domain.model.ReportByCountry
import com.squareup.moshi.Json

data class ReportByCountryResponse(

    @field:Json(name = "date")
    val date: String,

    @field:Json(name = "country")
    val country: String,

    @field:Json(name = "province")
    val province: List<ProvinceItem>,

    @field:Json(name = "latitude")
    val latitude: Int,

    @field:Json(name = "longitude")
    val longitude: Int
)

data class ProvinceItem(

    @field:Json(name = "recovered")
    val recovered: Int,

    @field:Json(name = "active")
    val active: Int,

    @field:Json(name = "confirmed")
    val confirmed: Int,

    @field:Json(name = "deaths")
    val deaths: Int
)

fun ReportByCountryResponse.toDomain() =
    ReportByCountry(date = date, country = country, province = province.map { it.toDomain() }, latitude = latitude, longitude = longitude)

private fun ProvinceItem.toDomain() = com.david.hackro.stats.domain.model.ProvinceItem(recovered = recovered, active = active, confirmed = confirmed, deaths = deaths)
