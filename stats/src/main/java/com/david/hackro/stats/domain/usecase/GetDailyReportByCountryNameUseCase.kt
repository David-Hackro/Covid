package com.david.hackro.stats.domain.usecase

import com.david.hackro.domain.UseCase
import com.david.hackro.stats.data.repository.StatsRepository
import com.david.hackro.stats.domain.model.ReportByCountry

class GetDailyReportByCountryNameUseCase(private val repository: StatsRepository) :
    UseCase<List<ReportByCountry>, GetDailyReportByCountryNameUseCase.Params>() {

    override suspend fun run(params: GetDailyReportByCountryNameUseCase.Params) =
        repository.getDailyReportByCountryName(date = params.date, name = params.name)

    data class Params(val name: String, val date: String)
}
