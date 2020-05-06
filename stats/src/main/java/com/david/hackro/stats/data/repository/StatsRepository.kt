package com.david.hackro.stats.data.repository

import com.david.hackro.domain.Either
import com.david.hackro.domain.Failure
import com.david.hackro.stats.domain.model.Report
import com.david.hackro.stats.domain.model.Totals

interface StatsRepository {

    suspend fun getLatestCountryDataByName(name: String, date: String): Either<Failure, List<Report>>

    suspend fun getDailyReportAllCountries(date: String): Either<Failure, List<Report>>

    suspend fun getLatestTotals(): Either<Failure, List<Totals>>
}
