package com.david.hackro.stats.domain.usecase

import com.david.hackro.domain.UseCase
import com.david.hackro.stats.data.repository.StatsRepository
import com.david.hackro.stats.domain.model.Country


class GetLatestCountryDataByNameUseCase(private val repository: StatsRepository) :
    UseCase<List<Country>, GetLatestCountryDataByNameUseCase.Params>() {

    override suspend fun run(params: GetLatestCountryDataByNameUseCase.Params) = repository.getLatestCountryDataByName(name = params.name)

    data class Params(val name: String)

}
