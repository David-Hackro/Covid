package com.david.hackro.stats.domain.usecase

import com.david.hackro.domain.UseCase
import com.david.hackro.stats.data.repository.StatsRepository
import com.david.hackro.stats.domain.model.ReportByCountry

class GetDailyReportByCountryCodeUseCase(private val repository: StatsRepository) :
    UseCase<List<ReportByCountry>, GetDailyReportByCountryCodeUseCase.Params>() {

    override suspend fun run(params: GetDailyReportByCountryCodeUseCase.Params) =
        repository.getDailyReportByCountryCode(date = params.date, code = params.code)

    data class Params(val date: String, val code: String)
}
