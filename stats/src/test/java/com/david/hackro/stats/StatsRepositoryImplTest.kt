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
import com.david.hackro.stats.data.datasource.remote.model.DataByStatusResponse
import com.david.hackro.stats.data.datasource.remote.model.SummaryInfoResponse
import com.david.hackro.stats.data.datasource.remote.model.toDomain
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

        test("get summary info") {
            //Given
            val response: SummaryInfoResponse = mockk(relaxed = RELAXED_TRUE)

            every { networkHandler.isConnected } returns NETWORK_CONNECTED

            coEvery {
                remoteDataSource.getSummaryInfo()
            } returns response

            //When
            runBlocking {
                val result = repositoryImpl.getSummaryInfo()
                Assert.assertEquals((result as Either.Right).b, response.toDomain())
            }

            //Then
            verify { networkHandler.isConnected }
            verify(exactly = VERIFY_ONE_INTERACTION) { networkHandler.isConnected }

            coVerify { remoteDataSource.getSummaryInfo() }

            confirmVerified(networkHandler, remoteDataSource)
        }

        test("get summary info fail when isn't connect") {
            //Given
            every { networkHandler.isConnected } returns NETWORK_DISCONNECTED

            //When
            runBlocking {
                val result = repositoryImpl.getSummaryInfo()
                Assert.assertEquals((result as Either.Left).a, Failure.NetworkConnection)
            }

            //Then
            verify(exactly = VERIFY_ONE_INTERACTION) { networkHandler.isConnected }
            coVerify(exactly = VERIFY_ZERO_INTERACTIONS) { remoteDataSource.getSummaryInfo() }
            confirmVerified(networkHandler, remoteDataSource)
        }

        test("get data by status success") {
            //Given
            val response: List<DataByStatusResponse> = mockk(relaxed = RELAXED_TRUE)
            val status = "confirmed"

            every { networkHandler.isConnected } returns NETWORK_CONNECTED

            coEvery {
                remoteDataSource.getDataByStatus(status = status)
            } returns response

            ///When
            runBlocking {
                val result = repositoryImpl.getDataByStatus(status = status)
                Assert.assertEquals((result as Either.Right).b, response.map { it.toDomain() })
            }

            //Then
            verify { networkHandler.isConnected }
            verify(exactly = VERIFY_ONE_INTERACTION) { networkHandler.isConnected }

            coVerify { remoteDataSource.getDataByStatus(status = status) }

            confirmVerified(networkHandler, remoteDataSource)
        }

        test("get data by status fail when isn't connect") {
            //Given
            every { networkHandler.isConnected } returns NETWORK_DISCONNECTED
            val status = "confirmed"

            //When
            runBlocking {
                val result = repositoryImpl.getDataByStatus(status = status)
                Assert.assertEquals((result as Either.Left).a, Failure.NetworkConnection)
            }

            //Then
            verify(exactly = VERIFY_ONE_INTERACTION) { networkHandler.isConnected }
            coVerify(exactly = VERIFY_ZERO_INTERACTIONS) { remoteDataSource.getDataByStatus(status = status) }
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
