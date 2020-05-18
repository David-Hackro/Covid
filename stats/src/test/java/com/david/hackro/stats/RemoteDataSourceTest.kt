package com.david.hackro.stats

import com.david.hackro.stats.RemoteDataSourceTest.RELAXED_TRUE
import com.david.hackro.stats.RemoteDataSourceTest.VERIFY_ONE_INTERACTION
import com.david.hackro.stats.data.datasource.remote.StatsApi
import com.david.hackro.stats.data.datasource.remote.StatsRemoteDataSource
import com.david.hackro.stats.data.datasource.remote.model.DataByStatusResponse
import com.david.hackro.stats.data.datasource.remote.model.SummaryInfoResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.spekframework.spek2.Spek

val statsApi: StatsApi = mockk()
val remoteDataSource = StatsRemoteDataSource(statsApi)

object RemoteDataSourceTest : Spek({
    group("summary report") {
        //getLatestCountryDataByName
        test("get summary info") {
            //Given
            val response: SummaryInfoResponse = mockk(relaxed = RELAXED_TRUE)

            coEvery {
                statsApi.getSummaryInfo()
            } returns response

            //When
            runBlocking {
                val result = remoteDataSource.getSummaryInfo()
                Assert.assertEquals(result, response)
            }
            //Then
            coVerify { statsApi.getSummaryInfo() }
            coVerify(exactly = VERIFY_ONE_INTERACTION) { statsApi.getSummaryInfo() }
        }

        test("get data by status") {
            //Given
            val response: List<DataByStatusResponse> = mockk(relaxed = RELAXED_TRUE)
            val status = "confirmed"

            coEvery {
                statsApi.getDataByStatus(status = status)
            } returns response

            //When
            runBlocking {
                val result = remoteDataSource.getDataByStatus(status = status)
                Assert.assertEquals(result, response)
            }

            //Then
            coVerify { statsApi.getDataByStatus(status = status) }
            coVerify(exactly = VERIFY_ONE_INTERACTION) { statsApi.getDataByStatus(status = status) }
        }
    }
}) {
    private const val RELAXED_TRUE = true
    private const val VERIFY_ONE_INTERACTION = 1
}
