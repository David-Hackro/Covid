package com.david.hackro.stats.domain.usecase

import com.david.hackro.domain.UseCase
import com.david.hackro.stats.data.repository.StatsRepository
import com.david.hackro.stats.domain.model.TotalsReport

class GetDailyReportTotalsUseCase(private val repository: StatsRepository) :
    UseCase<List<TotalsReport>, GetDailyReportTotalsUseCase.Params>() {

    override suspend fun run(params: GetDailyReportTotalsUseCase.Params) = repository.getDailyReportTotals(date = params.date)

    data class Params(val date: String)
}
