package com.david.hackro.stats.di

import com.david.hackro.androidext.NetworkHandler
import com.david.hackro.network.createNetworkClient
import com.david.hackro.stats.BuildConfig
import com.david.hackro.stats.BuildConfig.BASE_URL
import com.david.hackro.stats.BuildConfig.BASE_URL_OPEN_COVID
import com.david.hackro.stats.data.datasource.remote.StatsApi
import com.david.hackro.stats.data.datasource.remote.StatsOpenApi
import com.david.hackro.stats.data.datasource.remote.StatsRemoteDataSource
import com.david.hackro.stats.data.repository.StatsRepository
import com.david.hackro.stats.data.repository.StatsRepositoryImpl
import com.david.hackro.stats.domain.usecase.GetDailyReportAllCountriesUseCase
import com.david.hackro.stats.domain.usecase.GetDailyReportByCountryCodeUseCase
import com.david.hackro.stats.domain.usecase.GetDailyReportByCountryNameUseCase
import com.david.hackro.stats.domain.usecase.GetDailyReportTotalsUseCase
import com.david.hackro.stats.domain.usecase.GetDataLatestUseCase
import com.david.hackro.stats.domain.usecase.GetLatestAllCountriesUseCase
import com.david.hackro.stats.domain.usecase.GetLatestCountryDataByCodeUseCase
import com.david.hackro.stats.domain.usecase.GetLatestCountryDataByNameUseCase
import com.david.hackro.stats.domain.usecase.GetLatestTotalsUseCase
import com.david.hackro.stats.domain.usecase.GetListOfCountriesUseCase
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

private const val API_NAME_RAPIDAPI = "rapidapi"
private const val API_NAME_OPEN_COVID = "open-covid"

val useCaseModule: Module = module {
    factory { GetDailyReportAllCountriesUseCase(repository = get()) }
    factory { GetDailyReportByCountryCodeUseCase(repository = get()) }
    factory { GetDailyReportByCountryNameUseCase(repository = get()) }
    factory { GetDailyReportTotalsUseCase(repository = get()) }
    factory { GetLatestAllCountriesUseCase(repository = get()) }
    factory { GetLatestCountryDataByCodeUseCase(repository = get()) }
    factory { GetLatestCountryDataByNameUseCase(repository = get()) }
    factory { GetLatestTotalsUseCase(repository = get()) }
    factory { GetListOfCountriesUseCase(repository = get()) }
    factory { GetDataLatestUseCase(repository = get()) }
}

val repositoryModule: Module = module {
    single { StatsRepositoryImpl(networkHandler = get(), remoteDataSource = get()) as StatsRepository }
}

val dataSourceModule: Module = module {
    single { StatsRemoteDataSource(statsApi = get(), statsOpenApi = get()) }
}

val networkModule: Module = module {
    single { get<Retrofit>(named(API_NAME_RAPIDAPI)).create(StatsApi::class.java) }
    single(named(API_NAME_RAPIDAPI)) { createNetworkClient(baseUrl = BASE_URL, debug = BuildConfig.DEBUG, context = get()) }

    single { get<Retrofit>(named(API_NAME_OPEN_COVID)).create(StatsOpenApi::class.java) }
    single(named(API_NAME_OPEN_COVID)) { createNetworkClient(baseUrl = BASE_URL_OPEN_COVID, debug = BuildConfig.DEBUG, context = get()) }
}

val networkHandlerModule: Module = module {
    single { NetworkHandler(context = get()) }
}
