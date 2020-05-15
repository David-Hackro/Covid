package com.david.hackro.covid.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.hackro.domain.Failure
import com.david.hackro.domain.State
import com.david.hackro.stats.domain.model.CountryItem
import com.david.hackro.stats.domain.model.DataByStatus
import com.david.hackro.stats.domain.usecase.GetCountryListUseCase
import com.david.hackro.stats.domain.usecase.GetDataByStatusUseCase

class DailyReportAllCountriesViewModel(private val getCountryListUseCase: GetCountryListUseCase) : ViewModel() {

    private val _stateDailyReport = MutableLiveData<State>()
    val stateDailyReport: LiveData<State>
        get() = _stateDailyReport

    fun init() {
        getDataByStatus(status = DEFAULT_STATUS)
    }

    private fun getDataByStatus(status: String) {
        val params = GetCountryListUseCase.Params(status = status)

        getCountryListUseCase.invoke(viewModelScope, params) {
            it.either(::handleDataByStatusFailure, ::handleDataByStatusSuccess)
        }
    }

    private fun handleDataByStatusFailure(failure: Failure) {
        _stateDailyReport.value = State.Failed(failure)
    }

    private fun handleDataByStatusSuccess(countryList: List<CountryItem>) {
        _stateDailyReport.value = State.Success(countryList)
    }

    private companion object {
        const val DEFAULT_STATUS = "confirmed"
    }
}
