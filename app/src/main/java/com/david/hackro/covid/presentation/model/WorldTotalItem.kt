package com.david.hackro.covid.presentation.model

import com.david.hackro.covid.presentation.adapter.STATUS
import com.david.hackro.stats.domain.model.SummaryInfo

class WorldTotalItem(val max: Int, val progress: Int, val status: STATUS, val rate: Double)

fun SummaryInfo.toItemList() = listOf(
    WorldTotalItem(
        max = confirmed.value + recovered.value + deaths.value,
        progress = confirmed.value,
        status = STATUS.CONFIRMED,
        rate = ((confirmed.value.toDouble() / confirmed.value.toDouble()) * 100)
    ),
    WorldTotalItem(
        max = confirmed.value,
        progress = deaths.value,
        status = STATUS.DEATHS,
        rate = ((deaths.value.toDouble() / confirmed.value.toDouble()) * 100)
    ),
    WorldTotalItem(
        max = confirmed.value,
        progress = recovered.value,
        status = STATUS.RECOVERED,
        rate = ((recovered.value.toDouble() / confirmed.value.toDouble()) * 100)
    )
)
