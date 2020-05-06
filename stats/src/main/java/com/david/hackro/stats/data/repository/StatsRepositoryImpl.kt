package com.david.hackro.stats.data.repository

import com.david.hackro.androidext.NetworkHandler
import com.david.hackro.domain.Either
import com.david.hackro.domain.Failure
import com.david.hackro.stats.data.datasource.remote.StatsRemoteDataSource
import com.david.hackro.stats.data.datasource.remote.model.rapidapi.toDomain

class StatsRepositoryImpl(
    private val networkHandler: NetworkHandler,
    private val remoteDataSource: StatsRemoteDataSource
) : StatsRepository {

    override suspend fun getLatestCountryDataByName(name: String, date: String) = try {
        when (networkHandler.isConnected) {
            true -> Either.Right(remoteDataSource.getLatestCountryDataByName(name = name, date = date).map { it.toDomain() })
            else -> Either.Left(Failure.NetworkConnection)
        }
    } catch (ex: Exception) {
        Either.Left(Failure.GenericError(ex))
    }

    override suspend fun getDailyReportAllCountries(date: String) = try {
        when (networkHandler.isConnected) {
            true -> Either.Right(remoteDataSource.getDailyReportAllCountries(date = date).map { it.toDomain() })
            else -> Either.Left(Failure.NetworkConnection)
        }
    } catch (ex: Exception) {
        Either.Left(Failure.GenericError(ex))
    }

    override suspend fun getLatestTotals() = try {
        when (networkHandler.isConnected) {
            true -> Either.Right(remoteDataSource.getLatestTotals().map { it.toDomain() })
            else -> Either.Left(Failure.NetworkConnection)
        }
    } catch (ex: Exception) {
        Either.Left(Failure.GenericError(ex))
    }
}
