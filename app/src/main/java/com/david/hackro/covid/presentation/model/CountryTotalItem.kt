package com.david.hackro.covid.presentation.model

class CountryTotalItem(val max: Int, val progress: Int, val status: STATUS, val rate: Double)

fun CountryItem.toItemList() = listOf(

    CountryTotalItem(
        max = confirmed + recovered + death,
        progress = confirmed,
        status = STATUS.CONFIRMED,
        rate = ((confirmed.toDouble() / confirmed.toDouble()) * 100)
    ),
    CountryTotalItem(
        max = confirmed,
        progress = death,
        status = STATUS.DEATHS,
        rate = ((death.toDouble() / confirmed.toDouble()) * 100)
    ),
    CountryTotalItem(
        max = confirmed,
        progress = recovered,
        status = STATUS.RECOVERED,
        rate = ((recovered.toDouble() / confirmed.toDouble()) * 100)
    ),
    CountryTotalItem(
        max = confirmed,
        progress = active,
        status = STATUS.ACTIVE,
        rate = ((active.toDouble() / confirmed.toDouble()) * 100)
    )

)
