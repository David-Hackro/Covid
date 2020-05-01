package com.david.hackro.stats.data.datasource.remote.model

import com.david.hackro.stats.domain.model.Provinces
import com.squareup.moshi.Json

data class ProvincesResponse(

    @field:Json(name = "data")
    val data: List<DataItemProvinces>
)


data class DataItemProvinces(

    @field:Json(name = "iso")
    val iso: String,

    @field:Json(name = "province")
    val province: String,

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "lat")
    val lat: String,

    @field:Json(name = "long")
    val jsonMemberLong: String
)

fun ProvincesResponse.toDomain() = Provinces(data = data.map { it.toDomain() })

private fun DataItemProvinces.toDomain() =
    com.david.hackro.stats.domain.model.DataItemProvinces(
        iso = iso,
        province = province,
        name = name,
        lat = lat,
        jsonMemberLong = jsonMemberLong
    )
