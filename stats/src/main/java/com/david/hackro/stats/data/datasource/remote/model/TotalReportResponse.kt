package com.david.hackro.stats.data.datasource.remote.model

import com.david.hackro.stats.domain.TotalReport
import com.squareup.moshi.Json

data class TotalReportResponse(@field:Json(name = "data") val data: Data)

data class Data(

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

    @field:Json(name = "confirmed")
    val confirmed: Int,

    @field:Json(name = "deaths")
    val deaths: Int
)

fun TotalReportResponse.toDomain() = TotalReport(data = data.toDomain())

private fun Data.toDomain() = com.david.hackro.stats.domain.Data(
    date,
    confirmedDiff,
    activeDiff,
    deathsDiff,
    recovered,
    recoveredDiff,
    fatalityRate,
    lastUpdate,
    active,
    confirmed,
    deaths
)
