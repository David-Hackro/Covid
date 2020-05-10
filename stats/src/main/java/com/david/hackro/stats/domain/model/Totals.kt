package com.david.hackro.stats.domain.model

data class Totals(

	val recovered: Int,

	val critical: Int,

	val lastUpdate: String?,

	val lastChange: String?,

	val confirmed: Int,

	val deaths: Int
)
