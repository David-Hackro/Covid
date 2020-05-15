package com.david.hackro.stats.domain.usecase

import com.david.hackro.domain.UseCase
import com.david.hackro.stats.data.repository.StatsRepository
import com.david.hackro.stats.domain.model.CountryItem

class GetCountryListUseCase(private val repository: StatsRepository) : UseCase<List<CountryItem>, GetCountryListUseCase.Params>() {

    override suspend fun run(params: Params) = repository.getCountryList(status = params.status)

    data class Params(val status: String)
}
