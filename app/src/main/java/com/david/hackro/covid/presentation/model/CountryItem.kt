package com.david.hackro.covid.presentation.model

import com.david.hackro.stats.domain.model.DataByStatusItem

data class CountryItem(
    val countryName: String,
    val countryIso1: String,
    val countryIso2: String,
    val confirmed: Int,
    val recovered: Int,
    val death: Int,
    val active: Int
)

fun List<DataByStatusItem>.toCountryList(): List<CountryItem> {
    val countryList = mutableListOf<CountryItem>()

    this.groupBy { it.countryRegion }.apply {
        this.forEach { (key, value) ->
            var confirmed = 0
            var recovered = 0
            var death = 0
            var active = 0

            value.map {
                confirmed += it.confirmed
                recovered += it.recovered
                death += it.deaths
                active += it.active
            }

            value.first().run { countryList.add(CountryItem(key, iso2.toString(), iso3.toString(), confirmed, recovered, death, active)) }
        }

        countryList.sortByDescending { it.confirmed }

        return countryList
    }
}
