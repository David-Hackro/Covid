package com.david.hackro.stats.data.repository

import com.david.hackro.domain.Either
import com.david.hackro.domain.Failure
import com.david.hackro.stats.domain.model.DataByStatus
import com.david.hackro.stats.domain.model.SummaryInfo

interface StatsRepository {

    suspend fun getSummaryInfo(): Either<Failure, SummaryInfo>

    suspend fun getDataByStatus(status: String): Either<Failure, DataByStatus>
}
