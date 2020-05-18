package com.david.hackro.stats.data.datasource.remote.model

import com.david.hackro.stats.domain.model.DataValue
import com.david.hackro.stats.domain.model.SummaryInfo
import com.squareup.moshi.Json

data class SummaryInfoResponse(

    @field:Json(name = "recovered")
    val recovered: RecoveredResponse,

    @field:Json(name = "confirmed")
    val confirmed: ConfirmedResponse,

    @field:Json(name = "deaths")
    val deaths: DeathsResponse,

    @field:Json(name = "lastUpdate")
    val lastUpdate: String
)

data class RecoveredResponse(

    @field:Json(name = "detail")
    val detail: String,

    @field:Json(name = "value")
    val value: Int
)

data class ConfirmedResponse(

    @field:Json(name = "detail")
    val detail: String,

    @field:Json(name = "value")
    val value: Int
)

data class DeathsResponse(

    @field:Json(name = "detail")
    val detail: String,

    @field:Json(name = "value")
    val value: Int
)

fun SummaryInfoResponse.toDomain() = SummaryInfo(
    recovered = recovered.toDomain(),
    confirmed = confirmed.toDomain(),
    deaths = deaths.toDomain(),
    lastUpdate = lastUpdate
)

private fun RecoveredResponse.toDomain() = DataValue(detail = detail, value = value)

private fun ConfirmedResponse.toDomain() = DataValue(detail = detail, value = value)

private fun DeathsResponse.toDomain() = DataValue(detail = detail, value = value)
