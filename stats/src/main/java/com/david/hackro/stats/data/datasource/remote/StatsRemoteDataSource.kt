package com.david.hackro.stats.data.datasource.remote

class StatsRemoteDataSource(private val statsApi: StatsApi) {
    suspend fun getRegions() = statsApi.getRegions()

    suspend fun getTotalReportByDate(date: String) = statsApi.getTotalReportByDate(date = date)
}
