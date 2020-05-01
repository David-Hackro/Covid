package com.david.hackro.stats.data.repository

import com.david.hackro.domain.Either
import com.david.hackro.domain.Failure
import com.david.hackro.stats.domain.Regions

interface StatsRepository {
    suspend fun getRegions(): Either<Failure, Regions>
}
