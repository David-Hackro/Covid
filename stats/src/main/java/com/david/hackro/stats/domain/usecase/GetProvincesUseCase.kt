package com.david.hackro.stats.domain.usecase

import com.david.hackro.domain.UseCase
import com.david.hackro.stats.data.repository.StatsRepository
import com.david.hackro.stats.domain.model.Provinces

class GetProvincesUseCase(private val repository: StatsRepository) : UseCase<Provinces, GetProvincesUseCase.Params>() {

    override suspend fun run(params: Params) = repository.getProvinces(iso = params.iso)

    data class Params(val iso: String)
}
