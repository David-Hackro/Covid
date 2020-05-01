package com.david.hackro.stats.data.repository

import com.david.hackro.domain.Either
import com.david.hackro.domain.Failure
import com.david.hackro.stats.domain.model.Provinces
import com.david.hackro.stats.domain.model.Regions
import com.david.hackro.stats.domain.model.ReportList
import com.david.hackro.stats.domain.model.TotalReport

interface StatsRepository {
    suspend fun getRegions(): Either<Failure, Regions>

    suspend fun getTotalReportByDate(date: String): Either<Failure, TotalReport>

    suspend fun getReportsList(
        date: String,
        query: String,
        iso: String,
        regionName: String,
        regionProvidence: String,
        cityName: String
    ) : Either<Failure, ReportList>

    suspend fun getProvinces(iso: String): Either<Failure, Provinces>

}
