package com.david.hackro.stats.data.repository

import com.david.hackro.androidext.NetworkHandler
import com.david.hackro.domain.Either
import com.david.hackro.domain.Failure
import com.david.hackro.stats.data.datasource.remote.StatsRemoteDataSource
import com.david.hackro.stats.data.datasource.remote.model.toDomain
import com.david.hackro.stats.domain.model.Totals

class StatsRepositoryImpl(
    private val networkHandler: NetworkHandler,
    private val remoteDataSource: StatsRemoteDataSource
) : StatsRepository {

    override suspend fun getLatestCountryDataByName(name: String) = try {
        when (networkHandler.isConnected) {
            true -> Either.Right(remoteDataSource.getLatestCountryDataByName(name = name).map { it.toDomain() })
            else -> Either.Left(Failure.NetworkConnection)
        }
    } catch (ex: Exception) {
        Either.Left(Failure.GenericError(ex))
    }

    override suspend fun getLatestAllCountries() = try {
        when (networkHandler.isConnected) {
            true -> Either.Right(remoteDataSource.getLatestAllCountries().map { it.toDomain() })
            else -> Either.Left(Failure.NetworkConnection)
        }
    } catch (ex: Exception) {
        Either.Left(Failure.GenericError(ex))
    }

    override suspend fun getLatestCountryDataByCode(code: String) = try {
        when (networkHandler.isConnected) {
            true -> Either.Right(remoteDataSource.getLatestCountryDataByCode(code = code).map { it.toDomain() })
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

    override suspend fun getDailyReportByCountryCode(
        code: String,
        date: String
    ) = try {
        when (networkHandler.isConnected) {
            true -> Either.Right(
                remoteDataSource.getDailyReportByCountryCode(
                    code = code,
                    date = date
                ).map { it.toDomain() })
            else -> Either.Left(Failure.NetworkConnection)
        }
    } catch (ex: Exception) {
        Either.Left(Failure.GenericError(ex))
    }

    override suspend fun getDailyReportByCountryName(
        name: String,
        date: String
    ) = try {
        when (networkHandler.isConnected) {
            true -> Either.Right(
                remoteDataSource.getDailyReportByCountryName(
                    name = name,
                    date = date
                ).map { it.toDomain() })
            else -> Either.Left(Failure.NetworkConnection)
        }
    } catch (ex: Exception) {
        Either.Left(Failure.GenericError(ex))
    }

    override suspend fun getDailyReportTotals(date: String) = try {
        when (networkHandler.isConnected) {
            true -> Either.Right(remoteDataSource.getDailyReportTotals(date = date).map { it.toDomain() })
            else -> Either.Left(Failure.NetworkConnection)
        }
    } catch (ex: Exception) {
        Either.Left(Failure.GenericError(ex))
    }

    override suspend fun getListOfCountries() = try {
        when (networkHandler.isConnected) {
            true -> Either.Right(remoteDataSource.getListOfCountries().map { it.toDomain() })
            else -> Either.Left(Failure.NetworkConnection)
        }
    } catch (ex: Exception) {
        Either.Left(Failure.GenericError(ex))
    }

    override suspend fun getLatestTotals(): Either<Failure, List<Totals>> = try {
        when (networkHandler.isConnected) {
            true -> Either.Right(remoteDataSource.getLatestTotals().map { it.toDomain() })
            else -> Either.Left(Failure.NetworkConnection)
        }
    } catch (ex: Exception) {
        Either.Left(Failure.GenericError(ex))
    }
}
