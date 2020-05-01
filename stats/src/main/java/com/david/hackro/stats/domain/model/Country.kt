package com.david.hackro.stats.domain.model

data class Country(

    val country: String,

    val recovered: Int,

    val critical: Int,

    val latitude: Double,

    val lastUpdate: String,

    val lastChange: String,

    val confirmed: Int,

    val deaths: Int,

    val longitude: Double
)
