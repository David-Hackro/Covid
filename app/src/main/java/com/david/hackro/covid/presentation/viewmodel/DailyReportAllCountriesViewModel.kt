package com.david.hackro.covid.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.hackro.domain.Failure
import com.david.hackro.domain.State
import com.david.hackro.kotlinext.yesterday
import com.david.hackro.stats.domain.model.Report
import com.david.hackro.stats.domain.usecase.GetDailyReportAllCountriesUseCase

class DailyReportAllCountriesViewModel(private val getDailyReportAllCountriesUseCase: GetDailyReportAllCountriesUseCase) : ViewModel() {

    private val _stateDailyReport = MutableLiveData<State>()
    val stateDailyReport: LiveData<State>
        get() = _stateDailyReport

    fun init() {
        getDailyReportAllCountries()
    }

    private fun getDailyReportAllCountries() {
        _stateDailyReport.value = State.Loading

        val params = GetDailyReportAllCountriesUseCase.Params(date = yesterday(DATE_FORMAT))

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

    private companion object {
        const val DATE_FORMAT = "YYYY-MM-DD"
    }
}
