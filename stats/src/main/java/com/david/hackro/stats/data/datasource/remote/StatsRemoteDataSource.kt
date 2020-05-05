package com.david.hackro.stats.data.datasource.remote

class StatsRemoteDataSource(private val statsApi: StatsApi, private val statsOpenApi: StatsOpenApi) {

    suspend fun getLatestCountryDataByName(name: String, date: String) = statsApi.getLatestCountryDataByName(name = name, date = date)

    suspend fun getLatestAllCountries() = statsApi.getLatestAllCountries()

    suspend fun getLatestCountryDataByCode(code: String) = statsApi.getLatestCountryDataByCode(code = code)

    suspend fun getDailyReportAllCountries(date: String) =
        statsApi.getDailyReportAllCountries(date = date)

    suspend fun getDailyReportByCountryCode(code: String, date: String) =
        statsApi.getDailyReportByCountryCode(code = code, date = date)

    suspend fun getDailyReportByCountryName(name: String, date: String) =
        statsApi.getDailyReportByCountryName(name = name, date = date)

    suspend fun getDailyReportTotals(date: String) = statsApi.getDailyReportTotals(date = date)

    suspend fun getListOfCountries() = statsApi.getListOfCountries()

    suspend fun getLatestTotals() = statsApi.getLatestTotals()

    suspend fun getDataLatest() = statsOpenApi.getDataLatest()
}
