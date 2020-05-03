package com.david.hackro.stats.domain.usecase

import com.david.hackro.domain.UseCase
import com.david.hackro.stats.data.repository.StatsRepository
import com.david.hackro.stats.domain.model.GetDataLatest

class GetDataLatestUseCase(private val repository: StatsRepository) : UseCase<List<GetDataLatest>, UseCase.None>() {

    override suspend fun run(params: UseCase.None) = repository.getDataLatest()
}
