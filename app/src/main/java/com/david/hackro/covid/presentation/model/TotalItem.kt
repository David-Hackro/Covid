package com.david.hackro.covid.presentation.model

import com.david.hackro.covid.presentation.adapter.STATUS
import com.david.hackro.stats.domain.model.Report
import com.david.hackro.stats.domain.model.Totals

class TotalItem(val max: Int, val progress: Int, val status: STATUS)

fun Report.toItemList() = listOf(
    TotalItem(max = total.confirmed, progress = total.confirmed, status = STATUS.CONFIRMED),
    TotalItem(max = total.confirmed, progress = total.active, status = STATUS.ACTIVE),
    TotalItem(max = total.confirmed, progress = total.recovered, status = STATUS.RECOVERED),
    TotalItem(max = total.confirmed, progress = total.deaths, status = STATUS.DEATHS)
)

fun Totals.toItemList() = listOf(
    TotalItem(max = confirmed, progress = confirmed, status = STATUS.CONFIRMED),
    TotalItem(max = confirmed, progress = recovered, status = STATUS.RECOVERED),
    TotalItem(max = confirmed, progress = critical, status = STATUS.CRITICAL),
    TotalItem(max = confirmed, progress = deaths, status = STATUS.DEATHS)
)
