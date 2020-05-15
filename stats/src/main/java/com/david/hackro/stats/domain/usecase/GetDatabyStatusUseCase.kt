package com.david.hackro.stats.domain.usecase

import com.david.hackro.domain.UseCase
import com.david.hackro.stats.data.repository.StatsRepository
import com.david.hackro.stats.domain.model.DataByStatus

class GetDataByStatusUseCase(private val repository: StatsRepository) :
    UseCase<DataByStatus, GetDataByStatusUseCase.Params>() {

    override suspend fun run(params: Params) = repository.getDataByStatus(status = params.status)

    data class Params(val status: String)
}
