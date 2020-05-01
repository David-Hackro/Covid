package com.david.hackro.stats.domain.usecase

import com.david.hackro.domain.UseCase
import com.david.hackro.stats.data.repository.StatsRepository
import com.david.hackro.stats.domain.model.Country

class GetLatestAllCountriesUseCase(private val repository: StatsRepository) : UseCase<List<Country>, UseCase.None>() {

    override suspend fun run(params: UseCase.None) = repository.getLatestAllCountries()
}
