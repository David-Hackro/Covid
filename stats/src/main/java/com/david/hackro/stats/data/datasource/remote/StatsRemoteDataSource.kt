package com.david.hackro.stats.data.datasource.remote

class StatsRemoteDataSource(private val statsApi: StatsApi) {
    suspend fun getRegions() = statsApi.getRegions()

    suspend fun getTotalReportByDate(date: String) = statsApi.getTotalReportByDate(date = date)

    suspend fun getReportsList(
        date: String,
        query: String,
        iso: String,
        regionName: String,
        regionProvidence: String,
        cityName: String
    ) = statsApi.getReportsList(
        date = date,
        query = query,
        iso = iso,
        regionName = regionName,
        regionProvidence = regionProvidence,
        cityName = cityName
    )

    suspend fun getProvinces(iso: String) = statsApi.getProvinces(iso = iso)
}
