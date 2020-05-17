package com.david.hackro.stats

import com.david.hackro.stats.RemoteDataSourceTest.RELAXED_TRUE
import com.david.hackro.stats.RemoteDataSourceTest.VERIFY_ONE_INTERACTION
import com.david.hackro.stats.data.datasource.remote.StatsApi
import com.david.hackro.stats.data.datasource.remote.StatsRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.spekframework.spek2.Spek

val statsApi: StatsApi = mockk()
val remoteDataSource = StatsRemoteDataSource(statsApi)

object RemoteDataSourceTest : Spek({

    group("report") {
        //getLatestCountryDataByName
        test("get latest country data by name") {
            //Given
            val response: List<ReportResponse> = mockk(relaxed = RELAXED_TRUE)
            val name = "Mexico"
            val date = "2020-05-01"

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

        test("get daily report all countries") {
            //Given
            val response: List<ReportResponse> = mockk(relaxed = RELAXED_TRUE)
            val date = "2020-05-01"

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

    group("total") {
        test("get latest totals") {
            //Given
            val response: List<TotalsResponse> = mockk(relaxed = RELAXED_TRUE)

            coEvery {
                statsApi.getLatestTotals()
            } returns response

            //When
            runBlocking {
                val result = remoteDataSource.getLatestTotals()
                Assert.assertEquals(result, response)
            }

            //Then
            coVerify { statsApi.getLatestTotals() }
            coVerify(exactly = VERIFY_ONE_INTERACTION) { statsApi.getLatestTotals() }
        }
    }
}) {
    private const val RELAXED_TRUE = true
    private const val VERIFY_ONE_INTERACTION = 1
}
