package com.david.hackro.stats.data.datasource.remote

import com.david.hackro.stats.BuildConfig.END_POINT_COUNTRY
import com.david.hackro.stats.BuildConfig.END_POINT_HELP
import com.david.hackro.stats.BuildConfig.END_POINT_REPORT
import com.david.hackro.stats.BuildConfig.END_POINT_TOTALS
import com.david.hackro.stats.data.datasource.remote.StatsApiConstants.EndPointParams.DATE_FORMAT
import com.david.hackro.stats.data.datasource.remote.StatsApiConstants.EndPointParams.QUERY_CODE
import com.david.hackro.stats.data.datasource.remote.StatsApiConstants.EndPointParams.QUERY_DATE
import com.david.hackro.stats.data.datasource.remote.StatsApiConstants.EndPointParams.QUERY_DATE_FORMAT
import com.david.hackro.stats.data.datasource.remote.StatsApiConstants.EndPointParams.QUERY_JSON_FORMAT
import com.david.hackro.stats.data.datasource.remote.StatsApiConstants.EndPointParams.QUERY_JSON_FORMAT_VALUE
import com.david.hackro.stats.data.datasource.remote.StatsApiConstants.EndPointParams.QUERY_NAME
import com.david.hackro.stats.data.datasource.remote.StatsApiConstants.EndPointPaths.ALL
import com.david.hackro.stats.data.datasource.remote.StatsApiConstants.EndPointPaths.CODE
import com.david.hackro.stats.data.datasource.remote.StatsApiConstants.EndPointPaths.COUNTRIES
import com.david.hackro.stats.data.datasource.remote.StatsApiConstants.EndPointPaths.COUNTRY
import com.david.hackro.stats.data.datasource.remote.StatsApiConstants.EndPointPaths.HELP
import com.david.hackro.stats.data.datasource.remote.StatsApiConstants.EndPointPaths.NAME
import com.david.hackro.stats.data.datasource.remote.StatsApiConstants.EndPointPaths.TOTALS
import com.david.hackro.stats.data.datasource.remote.model.rapidapi.CountryResponse
import com.david.hackro.stats.data.datasource.remote.model.rapidapi.HelpResponse
import com.david.hackro.stats.data.datasource.remote.model.rapidapi.ReportByCountryResponse
import com.david.hackro.stats.data.datasource.remote.model.rapidapi.ReportResponse
import com.david.hackro.stats.data.datasource.remote.model.rapidapi.TotalsReportResponse
import com.david.hackro.stats.data.datasource.remote.model.rapidapi.TotalsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StatsApi {

    @GET(END_POINT_REPORT+"{${COUNTRY}}/{${NAME}}")
    suspend fun getLatestCountryDataByName(
        @Path(COUNTRY) country: String = COUNTRY,
        @Path(NAME) path: String = NAME,
        @Query(QUERY_NAME) name: String,
        @Query(QUERY_DATE) date: String,
        @Query(QUERY_DATE_FORMAT) dateFormat: String  = DATE_FORMAT,
        @Query("format") format: String = QUERY_JSON_FORMAT_VALUE
    ): List<ReportResponse>

    @GET(END_POINT_COUNTRY)
    suspend fun getLatestAllCountries(
        @Path(ALL) path: String = ALL,
        @Query(QUERY_JSON_FORMAT) format: String = QUERY_JSON_FORMAT_VALUE
    ): List<CountryResponse>

    @GET(END_POINT_COUNTRY+"{${CODE}}")
    suspend fun getLatestCountryDataByCode(
        @Path(CODE) path: String = CODE,
        @Query(QUERY_CODE) code: String,
        @Query(QUERY_JSON_FORMAT_VALUE) format: String = QUERY_JSON_FORMAT_VALUE
    ): List<CountryResponse>

    @GET(END_POINT_REPORT+"{${COUNTRY}}/{${ALL}}")
    suspend fun getDailyReportAllCountries(
        @Path(COUNTRY) country: String = COUNTRY,
        @Path(ALL) all: String = ALL,
        @Query(QUERY_DATE) date: String,
        @Query(QUERY_DATE_FORMAT) dateFormat: String  = DATE_FORMAT,
        @Query(QUERY_JSON_FORMAT_VALUE) format: String = QUERY_JSON_FORMAT_VALUE
    ): List<ReportResponse>


    @GET(END_POINT_REPORT)
    suspend fun getDailyReportByCountryCode(
        @Path(COUNTRY) country: String = COUNTRY,
        @Path(CODE) codePath: String = CODE,
        @Query(QUERY_CODE) code: String, //ISO 3166-1 standard
        @Query(QUERY_DATE) date: String,
        @Query(QUERY_DATE_FORMAT) dateFormat: String  = DATE_FORMAT,
        @Query(QUERY_JSON_FORMAT_VALUE) format: String = QUERY_JSON_FORMAT_VALUE
    ): List<ReportByCountryResponse>

    @GET(END_POINT_REPORT)
    suspend fun getDailyReportByCountryName(
        @Path(COUNTRY) country: String = COUNTRY,
        @Path(NAME) codePath: String = NAME,
        @Query(QUERY_NAME) name: String,
        @Query(QUERY_DATE) date: String,
        @Query(QUERY_DATE_FORMAT) dateFormat: String  = DATE_FORMAT,
        @Query(QUERY_JSON_FORMAT_VALUE) format: String = QUERY_JSON_FORMAT_VALUE
    ): List<ReportByCountryResponse>

    @GET(END_POINT_REPORT)
    suspend fun getDailyReportTotals(
        @Path(TOTALS) country: String = TOTALS,
        @Query(QUERY_DATE) date: String,
        @Query(QUERY_DATE_FORMAT) dateFormat: String  = DATE_FORMAT,
        @Query(QUERY_JSON_FORMAT_VALUE) format: String = QUERY_JSON_FORMAT_VALUE
    ): List<TotalsReportResponse>

    @GET(END_POINT_HELP)
    suspend fun getListOfCountries(
        @Path(HELP) help: String = HELP,
        @Path(COUNTRIES) country: String = COUNTRIES,
        @Query(QUERY_JSON_FORMAT_VALUE) format: String = QUERY_JSON_FORMAT_VALUE
    ): List<HelpResponse>

    @GET(END_POINT_TOTALS)
    suspend fun getLatestTotals(
        @Query(QUERY_JSON_FORMAT_VALUE) format: String = QUERY_JSON_FORMAT_VALUE
    ): List<TotalsResponse>
}
