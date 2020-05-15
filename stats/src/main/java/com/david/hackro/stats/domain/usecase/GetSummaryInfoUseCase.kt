package com.david.hackro.stats.domain.usecase

import com.david.hackro.domain.UseCase
import com.david.hackro.stats.data.repository.StatsRepository
import com.david.hackro.stats.domain.model.SummaryInfo

class GetSummaryInfoUseCase(private val repository: StatsRepository) :
    UseCase<SummaryInfo, UseCase.None>() {

    override suspend fun run(params: None) = repository.getSummaryInfo()
}
