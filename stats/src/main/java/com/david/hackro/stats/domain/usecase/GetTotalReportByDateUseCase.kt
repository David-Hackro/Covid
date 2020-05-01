package com.david.hackro.stats.domain.usecase

import com.david.hackro.domain.UseCase
import com.david.hackro.stats.data.repository.StatsRepository
import com.david.hackro.stats.domain.model.TotalReport

class GetTotalReportByDateUseCase(private val repository: StatsRepository) : UseCase<TotalReport, GetTotalReportByDateUseCase.Params>() {

    override suspend fun run(params: Params) = repository.getTotalReportByDate(date = params.date)

    data class Params(val date: String)
}
