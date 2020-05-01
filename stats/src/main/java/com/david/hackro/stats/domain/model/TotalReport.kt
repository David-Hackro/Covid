package com.david.hackro.stats.domain.model

data class TotalReport(val data: Data)

data class Data(
    val date: String,

    val confirmedDiff: Int,

    val activeDiff: Int,

    val deathsDiff: Int,

    val recovered: Int,

    val recoveredDiff: Int,

    val fatalityRate: Double,

    val lastUpdate: String,

    val active: Int,

    val confirmed: Int,

    val deaths: Int
)
