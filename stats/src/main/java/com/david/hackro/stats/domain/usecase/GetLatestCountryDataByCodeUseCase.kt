package com.david.hackro.stats.domain.usecase

import com.david.hackro.domain.UseCase
import com.david.hackro.stats.data.repository.StatsRepository
import com.david.hackro.stats.domain.model.Country

class GetLatestCountryDataByCodeUseCase(private val repository: StatsRepository) :
    UseCase<List<Country>, GetLatestCountryDataByCodeUseCase.Params>() {

    override suspend fun run(params: GetLatestCountryDataByCodeUseCase.Params) = repository.getLatestCountryDataByCode(code = params.code)

    data class Params(val code: String)

}
