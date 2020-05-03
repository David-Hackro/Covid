package com.david.hackro.stats.data.datasource.remote

import com.david.hackro.stats.BuildConfig.END_POINT_DATA_LATEST
import com.david.hackro.stats.data.datasource.remote.StatsApiConstants.EndPointPaths.DATA_LATEST
import com.david.hackro.stats.data.datasource.remote.StatsApiConstants.EndPointPaths.DATA_LATEST_VALUE
import com.david.hackro.stats.data.datasource.remote.model.opencovid.GetDataLatestResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface StatsOpenApi {

    @GET("$END_POINT_DATA_LATEST{$DATA_LATEST}")
    suspend fun getDataLatest(
        @Path(DATA_LATEST) path: String = DATA_LATEST_VALUE
    ): List<GetDataLatestResponse>

}
