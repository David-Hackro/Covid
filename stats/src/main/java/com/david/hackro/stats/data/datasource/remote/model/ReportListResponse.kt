package com.david.hackro.stats.data.datasource.remote.model

import com.david.hackro.stats.domain.model.ReportList
import com.squareup.moshi.Json

data class ReportListResponse(
    @field:Json(name = "data")
    val data: List<DataItemReportList>
)

data class Region(

    @field:Json(name = "iso")
    val iso: String,

    @field:Json(name = "province")
    val province: String,

    @field:Json(name = "cities")
    val cities: List<CitiesItem>,

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "lat")
    val lat: String,

    @field:Json(name = "long")
    val jsonMemberLong: String
)

data class DataItemReportList(

    @field:Json(name = "date")
    val date: String,

    @field:Json(name = "confirmed_diff")
    val confirmedDiff: Int,

    @field:Json(name = "active_diff")
    val activeDiff: Int,

    @field:Json(name = "deaths_diff")
    val deathsDiff: Int,

    @field:Json(name = "recovered")
    val recovered: Int,

    @field:Json(name = "recovered_diff")
    val recoveredDiff: Int,

    @field:Json(name = "fatality_rate")
    val fatalityRate: Double,

    @field:Json(name = "last_update")
    val lastUpdate: String,

    @field:Json(name = "active")
    val active: Int,

    @field:Json(name = "region")
    val region: Region,

    @field:Json(name = "confirmed")
    val confirmed: Int,

    @field:Json(name = "deaths")
    val deaths: Int
)


data class CitiesItem(

    @field:Json(name = "date")
    val date: String,

    @field:Json(name = "confirmed_diff")
    val confirmedDiff: Int,

    @field:Json(name = "deaths_diff")
    val deathsDiff: Int,

    @field:Json(name = "last_update")
    val lastUpdate: String,

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "fips")
    val fips: Int,

    @field:Json(name = "confirmed")
    val confirmed: Int,

    @field:Json(name = "lat")
    val lat: String,

    @field:Json(name = "long")
    val jsonMemberLong: String,

    @field:Json(name = "deaths")
    val deaths: Int
)


fun ReportListResponse.toDomain() = ReportList(data = data.map { it.toDomain() })

private fun DataItemReportList.toDomain() = com.david.hackro.stats.domain.model.DataItemReportList(
    date = date,
    confirmedDiff = confirmedDiff,
    activeDiff = activeDiff,
    deathsDiff = deathsDiff,
    recovered = recovered,
    recoveredDiff = recoveredDiff,
    fatalityRate = fatalityRate,
    lastUpdate = lastUpdate,
    active = active,
    region = region.toDomain(),
    confirmed = confirmed,
    deaths = deaths
)

private fun Region.toDomain() = com.david.hackro.stats.domain.model.Region(
    iso = iso,
    province = province,
    cities = cities.map { it.toDomain() },
    name = name,
    lat = lat,
    jsonMemberLong = jsonMemberLong
)

private fun CitiesItem.toDomain() = com.david.hackro.stats.domain.model.CitiesItem(
    date = date,
    confirmedDiff = confirmedDiff,
    deathsDiff = deathsDiff,
    lastUpdate = lastUpdate,
    name = name,
    fips = fips,
    confirmed = confirmed,
    lat = lat,
    jsonMemberLong = jsonMemberLong,
    deaths = deaths
)
