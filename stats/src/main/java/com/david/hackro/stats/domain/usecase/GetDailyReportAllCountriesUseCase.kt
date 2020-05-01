package com.david.hackro.stats.domain.usecase

import com.david.hackro.domain.UseCase
import com.david.hackro.stats.data.repository.StatsRepository
import com.david.hackro.stats.domain.model.Report

class GetDailyReportAllCountriesUseCase(private val repository: StatsRepository) :
    UseCase<List<Report>, GetDailyReportAllCountriesUseCase.Params>() {

    override suspend fun run(params: GetDailyReportAllCountriesUseCase.Params) = repository.getDailyReportAllCountries(date = params.date)

    data class Params(val date: String, val dateFormat: String)
}
