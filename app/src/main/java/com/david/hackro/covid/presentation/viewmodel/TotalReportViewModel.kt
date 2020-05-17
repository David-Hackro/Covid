package com.david.hackro.covid.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.hackro.covid.presentation.model.CountryItem
import com.david.hackro.domain.Failure
import com.david.hackro.domain.State
import com.david.hackro.domain.UseCase
import com.david.hackro.stats.domain.model.DataByStatus
import com.david.hackro.stats.domain.model.SummaryInfo
import com.david.hackro.stats.domain.usecase.GetDataByStatusUseCase
import com.david.hackro.stats.domain.usecase.GetSummaryInfoUseCase

class TotalReportViewModel(
    private val getSummaryInfoUseCase: GetSummaryInfoUseCase,
    private val getDataByStatusUseCase: GetDataByStatusUseCase
) : ViewModel() {

    private val _stateSummaryInfo = MutableLiveData<State>()
    val stateSummaryInfo: LiveData<State>
        get() = _stateSummaryInfo

    private val _stateCountryData = MutableLiveData<State>()
    val stateCountryData: LiveData<State>
        get() = _stateCountryData

    fun init() {
        getTotalReport()
        getDataByStatus()
    }

    private fun getTotalReport() {
        _stateSummaryInfo.value = State.Loading

        getSummaryInfoUseCase.invoke(viewModelScope, UseCase.None()) {
            it.either(::handleTotalReportFailure, ::handleTotalReportSuccess)
        }
    }

    private fun handleTotalReportFailure(failure: Failure) {
        _stateSummaryInfo.value = State.Failed(failure)
    }

    private fun handleTotalReportSuccess(summaryInfo: SummaryInfo) {
        _stateSummaryInfo.value = State.Success(summaryInfo)
    }


    private fun getDataByStatus() {
        val params = GetDataByStatusUseCase.Params(status = "confirmed")

        getDataByStatusUseCase.invoke(viewModelScope, params) {
            it.either(::handleDataByStatusFailure, ::handleDataByStatusSuccess)
        }
    }

    private fun handleDataByStatusFailure(failure: Failure) {

    }

    /*
        val countryName: String,
        val countryIso: String,
        val confirmed: Int,
        val recovered: Int,
        val death: Int,
        val active: Int
     */
    private fun handleDataByStatusSuccess(dataByStatus: DataByStatus) {
        val listddd = mutableListOf<CountryItem>()
        val d = dataByStatus.dataByStatusList.groupBy { it.countryRegion }


        d.forEach { (key, value) ->
            var confirmed = 0
            var recovered = 0
            var death = 0
            var active = 0

            value.map {
                confirmed += it.confirmed
                recovered += it.recovered
                death += it.deaths
                active += it.active
            }

            listddd.add(CountryItem(key, value.first().iso2.toString(), value.first().iso3.toString(), confirmed, recovered, death, active))
        }

        listddd.sortByDescending { it.confirmed }

        _stateCountryData.value = State.Success(listddd)

    }
}

