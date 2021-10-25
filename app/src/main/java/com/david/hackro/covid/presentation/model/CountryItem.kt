package com.david.hackro.covid.presentation.model

import com.david.hackro.stats.domain.model.DataByStatusItem
import com.github.mikephil.charting.data.BarEntry

const val ENTRY_CONFIRMED = 3F
const val ENTRY_DEATH = 2F
const val ENTRY_RECOVERED = 1F
const val ENTRY_ACTIVE = 0F

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
                it.recovered?.let {
                    recovered += it
                }
                it.active?.let {
                    active += it
                }
                death += it.deaths
            }

            value.first().run { countryList.add(CountryItem(key, iso2.toString(), iso3.toString(), confirmed, recovered, death, active)) }
        }

        countryList.sortByDescending { it.confirmed }

        return countryList
    }
}

fun CountryItem.toEntryList(): ArrayList<BarEntry> {
    val entries = ArrayList<BarEntry>()

    run {
        entries.add(BarEntry(ENTRY_CONFIRMED, confirmed.toFloat()))
        entries.add(BarEntry(ENTRY_DEATH, death.toFloat()))
        entries.add(BarEntry(ENTRY_RECOVERED, recovered.toFloat()))
        entries.add(BarEntry(ENTRY_ACTIVE, active.toFloat()))
    }

    return entries
}
