package com.david.hackro.covid.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.hackro.domain.Failure
import com.david.hackro.domain.State
import com.david.hackro.domain.UseCase
import com.david.hackro.kotlinext.getCurrentDateTime
import com.david.hackro.stats.domain.model.GetDataLatest
import com.david.hackro.stats.domain.model.Report
import com.david.hackro.stats.domain.usecase.GetDailyReportAllCountriesUseCase
import com.david.hackro.stats.domain.usecase.GetDataLatestUseCase

class MapViewModel(
    private val getDailyReportAllCountriesUseCase: GetDailyReportAllCountriesUseCase,
    private val getDataLatestUseCase: GetDataLatestUseCase
) : ViewModel() {

    private val _stateDailyReport = MutableLiveData<State>()
    val stateDailyReport: LiveData<State>
        get() = _stateDailyReport

    private val _stateDataLatest = MutableLiveData<State>()
    val stateDataLatest: LiveData<State>
        get() = _stateDataLatest

    fun init() {
        //getDailyReportAllCountries()
        getDataLatest()
    }

    private fun getDailyReportAllCountries() {
        _stateDailyReport.value = State.Loading

        val date = getCurrentDateTime()

        //date.toString(DATE_FORMAT)
        val params = GetDailyReportAllCountriesUseCase.Params(date = "2020-05-01")

        getDailyReportAllCountriesUseCase.invoke(viewModelScope, params) {
            it.either(::handleDailyReportAllCountriesFailure, ::handleDailyReportAllCountriesSuccess)
        }
    }

    private fun handleDailyReportAllCountriesFailure(failure: Failure) {
        _stateDailyReport.value = State.Failed(failure)
    }

    private fun handleDailyReportAllCountriesSuccess(list: List<Report>) {
        _stateDailyReport.value = State.Success(list)
    }

    private fun getDataLatest() {
        _stateDataLatest.value = State.Loading

        getDataLatestUseCase.invoke(viewModelScope, UseCase.None()) {
            it.either(::handleDataLatestFailure, ::handleDataLatestSuccess)
        }
    }

    private fun handleDataLatestFailure(failure: Failure) {
        _stateDataLatest.value = State.Failed(failure)
    }

    private fun handleDataLatestSuccess(list: List<GetDataLatest>) {
        _stateDataLatest.value = State.Success(list)
    }

    private companion object {
        const val DATE_FORMAT = "yyyy-MM-dd"
    }
}
