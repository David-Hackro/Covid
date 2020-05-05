package com.david.hackro.stats.domain.usecase

import com.david.hackro.domain.UseCase
import com.david.hackro.stats.data.repository.StatsRepository
import com.david.hackro.stats.domain.model.Country
import com.david.hackro.stats.domain.model.Report


class GetLatestCountryDataByNameUseCase(private val repository: StatsRepository) :
    UseCase<List<Report>, GetLatestCountryDataByNameUseCase.Params>() {

    override suspend fun run(params: Params) = repository.getLatestCountryDataByName(name = params.name, date = params.date)

    data class Params(val name: String, val date: String)

}
