package com.david.hackro.stats.data.datasource.remote.model.rapidapi

import com.david.hackro.stats.domain.model.Report
import com.david.hackro.stats.domain.model.Total
import com.squareup.moshi.Json

open class ReportResponse(

    @field:Json(name = "date")
    val date: String,

    @field:Json(name = "country")
    val country: String,

    @field:Json(name = "provinces")
    val provinces: List<ProvincesItem>,

    @field:Json(name = "latitude")
    val latitude: Double?,

    @field:Json(name = "longitude")
    val longitude: Double?
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
    Report(
        date = date,
        country = country,
        provinces = provinces.map { it.toDomain() },
        latitude = latitude,
        longitude = longitude,
        total = loadTotalForCountry(provinces)
    )


fun loadTotalForCountry(provinces: List<ProvincesItem?>): Total {
    var recoveredTotal = 0
    var activeTotal = 0
    var confirmedTotal = 0
    var deathsTotal = 0

    provinces.map {
        it?.run {
            recoveredTotal += this.recovered
            activeTotal += this.active
            confirmedTotal += this.confirmed
            deathsTotal += this.deaths
        }
    }

    return Total(recovered = recoveredTotal, active = activeTotal, confirmed = confirmedTotal, deaths = deathsTotal)
}


private fun ProvincesItem.toDomain() =
    com.david.hackro.stats.domain.model.ProvincesItem(
        recovered = recovered,
        province = province,
        active = active,
        confirmed = confirmed,
        deaths = deaths
    )
