package com.david.hackro.stats.domain.model

data class Report(

    val date: String,

    val country: String,

    val provinces: List<ProvincesItem?>,

    val latitude: Double?,

    val longitude: Double?,

    val total: Total
)

data class ProvincesItem(

    val recovered: Int,

    val province: String,

    val active: Int,

    val confirmed: Int,

    val deaths: Int
)

data class Total(
    var recovered: Int,

    var active: Int,

    var confirmed: Int,

    var deaths: Int
)
