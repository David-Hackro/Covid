package com.david.hackro.stats.domain.model

data class Report(

	val date: String,

	val country: String,

	val provinces: List<ProvincesItem?>,

	val latitude: Double,

	val longitude: Double
)

data class ProvincesItem(

	val recovered: Int,

	val province: String,

	val active: Int,

	val confirmed: Int,

	val deaths: Int
)
