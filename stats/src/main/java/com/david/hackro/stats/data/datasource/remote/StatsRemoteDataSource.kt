package com.david.hackro.stats.data.datasource.remote

class StatsRemoteDataSource(private val statsApi: StatsApi) {

    suspend fun getSummaryInfo() = statsApi.getSummaryInfo()

    suspend fun getDataByStatus(status: String) = statsApi.getDataByStatus(status = status)
}
