package com.david.hackro.covid.presentation.model

data class CountryItem(
    val regionCode: String?,

    val countryName: String,

    val regionName: String?,

    val deaths: Int?,

    val population: Int?,

    val latitude: String?,

    val confirmed: Int?,

    val countryCode: String,

    val longitude: String?,

    val date: String,

    val key: String
)
