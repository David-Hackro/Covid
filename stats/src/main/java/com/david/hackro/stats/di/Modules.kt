package com.david.hackro.stats.di

import com.david.hackro.androidext.NetworkHandler
import com.david.hackro.network.createNetworkClient
import com.david.hackro.stats.BuildConfig
import com.david.hackro.stats.BuildConfig.BASE_URL
import com.david.hackro.stats.data.datasource.remote.StatsApi
import com.david.hackro.stats.data.datasource.remote.StatsRemoteDataSource
import com.david.hackro.stats.data.repository.StatsRepository
import com.david.hackro.stats.data.repository.StatsRepositoryImpl
import com.david.hackro.stats.domain.usecase.GetDailyReportAllCountriesUseCase
import com.david.hackro.stats.domain.usecase.GetDailyReportByCountryCodeUseCase
import com.david.hackro.stats.domain.usecase.GetDailyReportByCountryNameUseCase
import com.david.hackro.stats.domain.usecase.GetDailyReportTotalsUseCase
import com.david.hackro.stats.domain.usecase.GetLatestAllCountriesUseCase
import com.david.hackro.stats.domain.usecase.GetLatestCountryDataByCodeUseCase
import com.david.hackro.stats.domain.usecase.GetLatestCountryDataByNameUseCase
import com.david.hackro.stats.domain.usecase.GetLatestTotalsUseCase
import com.david.hackro.stats.domain.usecase.GetListOfCountriesUseCase
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

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
}

val repositoryModule: Module = module {
    single { StatsRepositoryImpl(networkHandler = get(), remoteDataSource = get()) as StatsRepository }
}

val dataSourceModule: Module = module {
    single { StatsRemoteDataSource(statsApi = get()) }
}

val networkModule: Module = module {
    single { createNetworkClient(baseUrl = BASE_URL, debug = BuildConfig.DEBUG, context = get()) }
    single { get<Retrofit>().create(StatsApi::class.java) }
}

val networkHandlerModule: Module = module {
    single { NetworkHandler(context = get()) }
}
