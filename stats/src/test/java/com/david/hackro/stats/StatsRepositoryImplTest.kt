package com.david.hackro.stats

import com.david.hackro.androidext.NetworkHandler
import com.david.hackro.domain.Either
import com.david.hackro.domain.Failure
import com.david.hackro.stats.StatsRepositoryImplTest.NETWORK_CONNECTED
import com.david.hackro.stats.StatsRepositoryImplTest.NETWORK_DISCONNECTED
import com.david.hackro.stats.StatsRepositoryImplTest.RELAXED_TRUE
import com.david.hackro.stats.StatsRepositoryImplTest.VERIFY_ONE_INTERACTION
import com.david.hackro.stats.StatsRepositoryImplTest.VERIFY_ZERO_INTERACTIONS
import com.david.hackro.stats.data.datasource.remote.StatsRemoteDataSource
import com.david.hackro.stats.data.datasource.remote.model.rapidapi.ReportResponse
import com.david.hackro.stats.data.datasource.remote.model.rapidapi.TotalsResponse
import com.david.hackro.stats.data.datasource.remote.model.rapidapi.toDomain
import com.david.hackro.stats.data.repository.StatsRepositoryImpl
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.spekframework.spek2.Spek

object StatsRepositoryImplTest : Spek({

    val networkHandler: NetworkHandler = mockk()
    val remoteDataSource: StatsRemoteDataSource = mockk()
    val repositoryImpl = StatsRepositoryImpl(networkHandler, remoteDataSource)

    beforeEachTest {
        clearAllMocks()
    }

    group("report") {
        val name = "Mexico"
        val date = "2020-05-01"

        test("get latest country data by name success") {
            //Given
            val response: List<ReportResponse> = mockk(relaxed = RELAXED_TRUE)

            every { networkHandler.isConnected } returns NETWORK_CONNECTED

            coEvery {
                remoteDataSource.getLatestCountryDataByName(date = date, name = name)
            } returns response

            //When
            runBlocking {
                val result = repositoryImpl.getLatestCountryDataByName(date = date, name = name)
                Assert.assertEquals((result as Either.Right).b, response.map { it.toDomain() })
            }

            //Then
            verify { networkHandler.isConnected }
            verify(exactly = VERIFY_ONE_INTERACTION) { networkHandler.isConnected }

            coVerify { remoteDataSource.getLatestCountryDataByName(date = date, name = name) }

            confirmVerified(networkHandler, remoteDataSource)
        }

        test("get latest country data by name fail when isn't connect") {
            //Given
            every { networkHandler.isConnected } returns NETWORK_DISCONNECTED

            //When
            runBlocking {
                val result = repositoryImpl.getLatestCountryDataByName(name = name, date = date)
                Assert.assertEquals((result as Either.Left).a, Failure.NetworkConnection)
            }

            //Then
            verify(exactly = VERIFY_ONE_INTERACTION) { networkHandler.isConnected }
            coVerify(exactly = VERIFY_ZERO_INTERACTIONS) { remoteDataSource.getLatestCountryDataByName(date = date, name = name) }
            confirmVerified(networkHandler, remoteDataSource)
        }

        test("getDailyReportAllCountries success") {
            //Given
            val response: List<ReportResponse> = mockk(relaxed = RELAXED_TRUE)

            every { networkHandler.isConnected } returns NETWORK_CONNECTED

            coEvery {
                remoteDataSource.getDailyReportAllCountries(date = date)
            } returns response

            ///When
            runBlocking {
                val result = repositoryImpl.getDailyReportAllCountries(date = date)
                Assert.assertEquals((result as Either.Right).b, response.map { it.toDomain() })
            }

            //Then
            verify { networkHandler.isConnected }
            verify(exactly = VERIFY_ONE_INTERACTION) { networkHandler.isConnected }

            coVerify { remoteDataSource.getDailyReportAllCountries(date = date) }

            confirmVerified(networkHandler, remoteDataSource)
        }

        test("get daily report all countries fail when isn't connect") {
            //Given
            every { networkHandler.isConnected } returns NETWORK_DISCONNECTED

            //When
            runBlocking {
                val result = repositoryImpl.getDailyReportAllCountries(date = date)
                Assert.assertEquals((result as Either.Left).a, Failure.NetworkConnection)
            }

            //Then
            verify(exactly = VERIFY_ONE_INTERACTION) { networkHandler.isConnected }
            coVerify(exactly = VERIFY_ZERO_INTERACTIONS) { remoteDataSource.getDailyReportAllCountries(date = date) }
            confirmVerified(networkHandler, remoteDataSource)
        }

    }

    group("total") {
        test("get latest totals success") {
            //Given
            val response: List<TotalsResponse> = mockk(relaxed = RELAXED_TRUE)

            every { networkHandler.isConnected } returns NETWORK_CONNECTED

            coEvery {
                remoteDataSource.getLatestTotals()
            } returns response

            //When
            runBlocking {
                val result = repositoryImpl.getLatestTotals()
                Assert.assertEquals((result as Either.Right).b, response.map { it.toDomain() })
            }

            //Then
            verify { networkHandler.isConnected }
            verify(exactly = VERIFY_ONE_INTERACTION) { networkHandler.isConnected }

            coVerify { remoteDataSource.getLatestTotals() }

            confirmVerified(networkHandler, remoteDataSource)
        }

        test("get latest totals  fail when isn't connect") {
            //Given
            every { networkHandler.isConnected } returns NETWORK_DISCONNECTED

            //When
            runBlocking {
                val result = repositoryImpl.getLatestTotals()
                Assert.assertEquals((result as Either.Left).a, Failure.NetworkConnection)
            }

            //Then
            verify(exactly = VERIFY_ONE_INTERACTION) { networkHandler.isConnected }
            coVerify(exactly = VERIFY_ZERO_INTERACTIONS) { remoteDataSource.getLatestTotals() }
            confirmVerified(networkHandler, remoteDataSource)
        }
    }

}) {
    private const val VERIFY_ZERO_INTERACTIONS = 0
    private const val VERIFY_ONE_INTERACTION = 1
    private const val RELAXED_TRUE = true
    private const val NETWORK_CONNECTED = true
    private const val NETWORK_DISCONNECTED = false
}
