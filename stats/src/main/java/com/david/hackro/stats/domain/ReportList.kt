package com.david.hackro.stats.domain

data class ReportList(

    val data: List<DataItemReportList>
)

data class Region(

    val iso: String,

    val province: String,

    val cities: List<CitiesItem>,

    val name: String,

    val lat: String,

    val jsonMemberLong: String
)

data class DataItemReportList(

    val date: String,

    val confirmedDiff: Int,

    val activeDiff: Int,

    val deathsDiff: Int,

    val recovered: Int,

    val recoveredDiff: Int,

    val fatalityRate: Double,

    val lastUpdate: String,

    val active: Int,

    val region: Region,

    val confirmed: Int,

    val deaths: Int
)


data class CitiesItem(

    val date: String,

    val confirmedDiff: Int,

    val deathsDiff: Int,

    val lastUpdate: String,

    val name: String,

    val fips: Int,

    val confirmed: Int,

    val lat: String,

    val jsonMemberLong: String,

    val deaths: Int
)
