package com.david.hackro.stats.data.repository

import com.david.hackro.androidext.NetworkHandler
import com.david.hackro.domain.Either
import com.david.hackro.domain.Failure
import com.david.hackro.stats.data.datasource.remote.StatsRemoteDataSource
import com.david.hackro.stats.data.datasource.remote.model.toModel
import com.david.hackro.stats.domain.model.DataByStatus

class StatsRepositoryImpl(
    private val networkHandler: NetworkHandler,
    private val remoteDataSource: StatsRemoteDataSource
) : StatsRepository {

    override suspend fun getSummaryInfo() = try {
        when (networkHandler.isConnected) {
            true -> Either.Right(remoteDataSource.getSummaryInfo().toModel())
            else -> Either.Left(Failure.NetworkConnection)
        }
    } catch (ex: Exception) {
        Either.Left(Failure.GenericError(ex))
    }

    override suspend fun getDataByStatus(status: String) = try {
        when (networkHandler.isConnected) {
            true -> {
                Either.Right(
                    DataByStatus(
                        status = status,
                        dataByStatusList = remoteDataSource.getDataByStatus(status = status).map { it.toModel() })
                )
            }
            else -> Either.Left(Failure.NetworkConnection)
        }
    } catch (ex: Exception) {
        Either.Left(Failure.GenericError(ex))
    }

}
