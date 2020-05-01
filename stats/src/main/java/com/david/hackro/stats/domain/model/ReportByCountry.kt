package com.david.hackro.stats.domain.model

data class ReportByCountry(

    val date: String,

    val country: String,

    val province: List<ProvinceItem>,

    val latitude: Int,

    val longitude: Int
)

data class ProvinceItem(

    val recovered: Int,

    val active: Int,

    val confirmed: Int,

    val deaths: Int
)
