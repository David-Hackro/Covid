package com.david.hackro.stats.domain.model

data class SummaryInfo(
    val recovered: DataValue,
    val confirmed: DataValue,
    val deaths: DataValue,
    val lastUpdate: String
)

data class DataValue(
    val detail: String,
    val value: Int
)
