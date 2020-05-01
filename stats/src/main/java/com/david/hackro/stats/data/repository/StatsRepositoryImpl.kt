package com.david.hackro.stats.data.repository

import com.david.hackro.androidext.NetworkHandler
import com.david.hackro.domain.Either
import com.david.hackro.domain.Failure
import com.david.hackro.stats.data.datasource.remote.StatsRemoteDataSource
import com.david.hackro.stats.data.datasource.remote.toDomain

class StatsRepositoryImpl(
    private val networkHandler: NetworkHandler,
    private val remoteDataSource: StatsRemoteDataSource
) : StatsRepository {

    override suspend fun getRegions() =
        try {
            when (networkHandler.isConnected) {
                true -> Either.Right(remoteDataSource.getRegions().toDomain())
                else -> Either.Left(Failure.NetworkConnection)
            }
        } catch (ex: Exception) {
            Either.Left(Failure.GenericError(ex))
        }
}
