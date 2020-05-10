package com.david.hackro.stats

import com.david.hackro.stats.data.datasource.remote.StatsApi
import com.david.hackro.stats.data.datasource.remote.StatsRemoteDataSource
import com.david.hackro.stats.data.datasource.remote.model.rapidapi.ReportResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.spekframework.spek2.Spek

const val VERIFY_ONE_INTERACTION = 1
val statsApi: StatsApi = mockk()
val remoteDataSource = StatsRemoteDataSource(statsApi)

object RemoteDataSourceTest : Spek({

    group("report") {
        //getLatestCountryDataByName
        test("get Latest Country Data By Name") {
            //Given
            val response: List<ReportResponse> = mockk()
            val name = ""
            val date = ""

            coEvery {
                statsApi.getLatestCountryDataByName(name = name, date = date)
            } returns response

            //When
            runBlocking {
                val result = remoteDataSource.getLatestCountryDataByName(name = name, date = date)
                Assert.assertEquals(result, response)
            }
            //Then
            coVerify { statsApi.getLatestCountryDataByName(name = name, date = date) }
            coVerify(exactly = VERIFY_ONE_INTERACTION) { statsApi.getLatestCountryDataByName(name = name, date = date) }
        }

        test("get Daily Report All Countries") {
            //Given
            val response: List<ReportResponse> = mockk()
            val date = ""

            coEvery {
                statsApi.getDailyReportAllCountries(date = date)
            } returns response

            //When
            runBlocking {
                val result = remoteDataSource.getDailyReportAllCountries(date = date)
                Assert.assertEquals(result, response)
            }

            //Then
            coVerify { statsApi.getDailyReportAllCountries(date = date) }
            coVerify(exactly = VERIFY_ONE_INTERACTION) { statsApi.getDailyReportAllCountries(date = date) }
        }
    }

})
