package com.david.hackro.stats.data.repository

import com.david.hackro.domain.Either
import com.david.hackro.domain.Failure
import com.david.hackro.stats.domain.model.Country
import com.david.hackro.stats.domain.model.GetDataLatest
import com.david.hackro.stats.domain.model.Help
import com.david.hackro.stats.domain.model.Report
import com.david.hackro.stats.domain.model.ReportByCountry
import com.david.hackro.stats.domain.model.Totals
import com.david.hackro.stats.domain.model.TotalsReport

interface StatsRepository {

    suspend fun getLatestCountryDataByName(name: String): Either<Failure, List<Country>>

    suspend fun getLatestAllCountries(): Either<Failure, List<Country>>

    suspend fun getLatestCountryDataByCode(code: String): Either<Failure, List<Country>>

    suspend fun getDailyReportAllCountries(date: String): Either<Failure, List<Report>>

    suspend fun getDailyReportByCountryCode(code: String, date: String): Either<Failure, List<ReportByCountry>>

    suspend fun getDailyReportByCountryName(name: String, date: String): Either<Failure, List<ReportByCountry>>

    suspend fun getDailyReportTotals(date: String): Either<Failure, List<TotalsReport>>

    suspend fun getListOfCountries(): Either<Failure, List<Help>>

    suspend fun getLatestTotals(): Either<Failure, List<Totals>>

    suspend fun getDataLatest(): Either<Failure, List<GetDataLatest>>
}
