package com.david.hackro.covid.presentation.model

import com.david.hackro.covid.presentation.adapter.STATUS
import com.david.hackro.stats.domain.model.SummaryInfo

class TotalItem(val max: Int, val progress: Int, val status: STATUS)

fun SummaryInfo.toItemList() = listOf(
    TotalItem(max = confirmed.value, progress = confirmed.value, status = STATUS.CONFIRMED),
    TotalItem(max = confirmed.value, progress = recovered.value, status = STATUS.RECOVERED),
    TotalItem(max = confirmed.value, progress = deaths.value, status = STATUS.DEATHS)
)
