package com.david.hackro.stats.data.datasource.remote

import com.david.hackro.stats.BuildConfig.END_POINT_REGIONS
import com.david.hackro.stats.BuildConfig.END_POINT_REPORTS
import com.david.hackro.stats.data.datasource.remote.StatsApiConstants.EndPointParams.DATE
import com.david.hackro.stats.data.datasource.remote.StatsApiConstants.EndPointPaths.TOTAL
import com.david.hackro.stats.data.datasource.remote.model.RegionResponse
import com.david.hackro.stats.data.datasource.remote.model.TotalReportResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StatsApi {
    @GET(END_POINT_REGIONS)
    suspend fun getRegions(): RegionResponse

    @GET(END_POINT_REPORTS)
    suspend fun getTotalReportByDate(@Path(TOTAL) total: String = TOTAL, @Query(DATE) date: String): TotalReportResponse
}
