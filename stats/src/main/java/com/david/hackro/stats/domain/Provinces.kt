package com.david.hackro.stats.domain


data class Provinces(
    val data: List<DataItemProvinces>
)

data class DataItemProvinces(

    val iso: String,

    val province: String,

    val name: String,

    val lat: String,

    val jsonMemberLong: String
)
