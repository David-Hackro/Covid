package com.david.hackro.stats.data.datasource.remote.model.rapidapi

import com.david.hackro.stats.domain.model.TotalsReport
import com.squareup.moshi.Json

data class TotalsReportResponse(

    @field:Json(name = "date")
    val date: String,

    @field:Json(name = "recovered")
    val recovered: Int,

    @field:Json(name = "active")
    val active: Int,

    @field:Json(name = "confirmed")
    val confirmed: Int,

    @field:Json(name = "deaths")
    val deaths: Int
)

fun TotalsReportResponse.toDomain() =
    TotalsReport(date = date, recovered = recovered, active = active, confirmed = confirmed, deaths = deaths)
