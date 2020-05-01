package com.david.hackro.stats.data.datasource.remote

import com.david.hackro.stats.BuildConfig.END_POINT_REGIONS
import com.david.hackro.stats.BuildConfig.END_POINT_REPORTS
import com.david.hackro.stats.data.datasource.remote.StatsApiConstants.EndPointParams.CITY_NAME
import com.david.hackro.stats.data.datasource.remote.StatsApiConstants.EndPointParams.DATE
import com.david.hackro.stats.data.datasource.remote.StatsApiConstants.EndPointParams.ISO
import com.david.hackro.stats.data.datasource.remote.StatsApiConstants.EndPointParams.QUERY
import com.david.hackro.stats.data.datasource.remote.StatsApiConstants.EndPointParams.REGION_NAME
import com.david.hackro.stats.data.datasource.remote.StatsApiConstants.EndPointParams.REGION_PROVIDENCE
import com.david.hackro.stats.data.datasource.remote.StatsApiConstants.EndPointPaths.TOTAL
import com.david.hackro.stats.data.datasource.remote.model.RegionResponse
import com.david.hackro.stats.data.datasource.remote.model.ReportListResponse
import com.david.hackro.stats.data.datasource.remote.model.TotalReportResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StatsApi {
    @GET(END_POINT_REGIONS)
    suspend fun getRegions(): RegionResponse

    @GET(END_POINT_REPORTS)
    suspend fun getTotalReportByDate(@Path(TOTAL) total: String = TOTAL, @Query(DATE) date: String): TotalReportResponse

    @GET(END_POINT_REPORTS)
    suspend fun getReportsList(
        @Query(DATE) date: String = "",
        @Query(QUERY) query: String = "",
        @Query(ISO) iso: String = "",
        @Query(REGION_NAME) regionName: String = "",
        @Query(REGION_PROVIDENCE) regionProvidence: String = "",
        @Query(CITY_NAME) cityName: String  = ""
    ): ReportListResponse
}
