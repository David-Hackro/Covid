package com.david.hackro.stats.data.datasource.remote

class StatsRemoteDataSource(private val statsApi: StatsApi) {

    suspend fun getLatestCountryDataByName(name: String, date: String) = statsApi.getLatestCountryDataByName(name = name, date = date)

    suspend fun getDailyReportAllCountries(date: String) =
        statsApi.getDailyReportAllCountries(date = date)

    suspend fun getLatestTotals() = statsApi.getLatestTotals()
}
