package com.david.hackro.covid.presentation.model

private const val PERCENTAGE = 100

class CountryTotalItem(val max: Int, val progress: Int, val status: STATUS, val rate: Double)

fun CountryItem.toItemList() = listOf(

    CountryTotalItem(
        max = confirmed + recovered + death,
        progress = confirmed,
        status = STATUS.CONFIRMED,
        rate = ((confirmed.toDouble() / confirmed.toDouble()) * PERCENTAGE)
    ),
    CountryTotalItem(
        max = confirmed,
        progress = death,
        status = STATUS.DEATHS,
        rate = ((death.toDouble() / confirmed.toDouble()) * PERCENTAGE)
    ),
    CountryTotalItem(
        max = confirmed,
        progress = recovered,
        status = STATUS.RECOVERED,
        rate = ((recovered.toDouble() / confirmed.toDouble()) * PERCENTAGE)
    ),
    CountryTotalItem(
        max = confirmed,
        progress = active,
        status = STATUS.ACTIVE,
        rate = ((active.toDouble() / confirmed.toDouble()) * PERCENTAGE)
    )

)
