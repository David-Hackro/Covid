package com.david.hackro.stats.data.datasource.remote

import com.david.hackro.stats.BuildConfig.END_POINT_API
import com.david.hackro.stats.data.datasource.remote.StatsApiConstants.EndPointPaths.STATUS
import com.david.hackro.stats.data.datasource.remote.model.DataByStatusResponse
import com.david.hackro.stats.data.datasource.remote.model.SummaryInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface StatsApi {

    @GET(END_POINT_API)
    suspend fun getSummaryInfo(): SummaryInfoResponse

    @GET("$END_POINT_API/{$STATUS}")
    suspend fun getDataByStatus(@Path(STATUS) status: String): List<DataByStatusResponse>
}
