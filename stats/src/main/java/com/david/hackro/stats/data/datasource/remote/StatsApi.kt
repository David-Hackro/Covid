package com.david.hackro.stats.data.datasource.remote

import com.david.hackro.stats.BuildConfig.END_POINT_REGIONS
import retrofit2.http.GET

interface StatsApi {
    @GET(END_POINT_REGIONS)
    suspend fun getRegions(): RegionResponse
}
