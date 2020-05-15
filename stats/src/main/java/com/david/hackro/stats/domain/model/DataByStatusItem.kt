package com.david.hackro.stats.domain.model

data class DataByStatus(
    val status: String,
    val dataByStatusList: List<DataByStatusItem>
)

data class DataByStatusItem(
    val countryRegion: String,
    val active: Int,
    val confirmed: Int,
    val provinceState: String?,
    val lng: Float?,
    val incidentRate: Float?,
    val uid: Int,
    val recovered: Int,
    val lastUpdate: Long,
    val combinedKey: String,
    val iso2: String?,
    val lat: Float?,
    val deaths: Int,
    val iso3: String?
)

