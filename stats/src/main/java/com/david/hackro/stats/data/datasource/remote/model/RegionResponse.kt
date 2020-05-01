package com.david.hackro.stats.data.datasource.remote.model

import com.david.hackro.stats.domain.model.DataItem
import com.david.hackro.stats.domain.model.Regions
import com.squareup.moshi.Json

data class RegionResponse(@field:Json(name = "data") val data: List<DataItem>)

data class DataItem(
    @field:Json(name = "iso") val iso: String,
    @field:Json(name = "name") val name: String
)

fun RegionResponse.toDomain() = Regions(data.map { it.toDomain() })

fun DataItem.toDomain() = com.david.hackro.stats.data.datasource.remote.model.DataItem(iso = iso, name = name)
