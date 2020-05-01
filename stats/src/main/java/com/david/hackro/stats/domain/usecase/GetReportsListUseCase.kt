package com.david.hackro.stats.domain.usecase

import com.david.hackro.domain.UseCase
import com.david.hackro.stats.data.repository.StatsRepository
import com.david.hackro.stats.domain.model.ReportList

class GetReportsListUseCase(private val repository: StatsRepository) : UseCase<ReportList, GetReportsListUseCase.Params>() {

    override suspend fun run(params: Params) = repository.getReportsList(
        date = params.date, query = params.query, iso = params.iso,
        regionName = params.regionName, cityName = params.cityName, regionProvidence = params.regionProvidence
    )

    data class Params(
        val date: String,
        val query: String,
        val iso: String,
        val regionName: String,
        val regionProvidence: String,
        val cityName: String
    )
}
