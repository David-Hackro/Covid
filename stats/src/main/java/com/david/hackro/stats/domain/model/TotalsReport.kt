package com.david.hackro.stats.domain.model

data class TotalsReport(

    val date: String,

    val recovered: Int,

    val active: Int,

    val confirmed: Int,

    val deaths: Int
)
