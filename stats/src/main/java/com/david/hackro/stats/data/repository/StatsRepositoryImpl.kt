package com.david.hackro.stats.data.repository

import com.david.hackro.androidext.NetworkHandler
import com.david.hackro.domain.Either
import com.david.hackro.domain.Failure
import com.david.hackro.stats.data.datasource.remote.StatsRemoteDataSource
import com.david.hackro.stats.data.datasource.remote.model.toDomain

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

    override suspend fun getTotalReportByDate(date: String) = try {
        when (networkHandler.isConnected) {
            true -> {
                Either.Right(remoteDataSource.getTotalReportByDate(date = date).toDomain())
            }
            else -> Either.Left(Failure.NetworkConnection)
        }
    } catch (ex: Exception) {
        Either.Left(Failure.GenericError(ex))
    }

    override suspend fun getReportsList(
        date: String,
        query: String,
        iso: String,
        regionName: String,
        regionProvidence: String,
        cityName: String
    ) = try {
        when (networkHandler.isConnected) {
            true -> {
                Either.Right(
                    remoteDataSource.getReportsList(
                        date = date,
                        query = query,
                        iso = iso,
                        regionName = regionName,
                        regionProvidence = regionProvidence,
                        cityName = cityName
                    ).toDomain()
                )
            }
            else -> Either.Left(Failure.NetworkConnection)
        }
    } catch (ex: Exception) {
        Either.Left(Failure.GenericError(ex))
    }
}
