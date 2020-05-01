package com.david.hackro.stats.data.datasource.remote.model

import com.david.hackro.stats.domain.model.Totals
import com.squareup.moshi.Json

data class TotalsResponse(

    @field:Json(name = "recovered")
    val recovered: Int,

    @field:Json(name = "critical")
    val critical: Int,

    @field:Json(name = "lastUpdate")
    val lastUpdate: String,

    @field:Json(name = "lastChange")
    val lastChange: String,

    @field:Json(name = "confirmed")
    val confirmed: Int,

    @field:Json(name = "deaths")
    val deaths: Int
)

fun TotalsResponse.toDomain() = Totals(
    recovered = recovered,
    critical = critical,
    lastUpdate = lastUpdate,
    lastChange = lastChange,
    confirmed = confirmed,
    deaths = deaths
)
