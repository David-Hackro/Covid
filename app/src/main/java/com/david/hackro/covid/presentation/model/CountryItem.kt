package com.david.hackro.covid.presentation.model

data class CountryItem(
    val countryName: String,
    val countryIso1: String,
    val countryIso2: String,
    val confirmed: Int,
    val recovered: Int,
    val death: Int,
    val active: Int
)
