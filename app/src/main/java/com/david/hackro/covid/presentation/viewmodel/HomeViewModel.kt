package com.david.hackro.covid.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.hackro.covid.presentation.model.toCountryList
import com.david.hackro.domain.Failure
import com.david.hackro.domain.State
import com.david.hackro.domain.UseCase
import com.david.hackro.stats.domain.model.DataByStatus
import com.david.hackro.stats.domain.model.SummaryInfo
import com.david.hackro.stats.domain.usecase.GetDataByStatusUseCase
import com.david.hackro.stats.domain.usecase.GetSummaryInfoUseCase

class HomeViewModel(
    private val getSummaryInfoUseCase: GetSummaryInfoUseCase,
    private val getDataByStatusUseCase: GetDataByStatusUseCase
) : ViewModel() {

    private val _stateSummaryInfo = MutableLiveData<State>()
    val stateSummaryInfo: LiveData<State>
        get() = _stateSummaryInfo

    private val _stateDataByStatus = MutableLiveData<State>()
    val stateCountryData: LiveData<State>
        get() = _stateDataByStatus

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
        val params = GetDataByStatusUseCase.Params(status = DEFAULT_STATUS)

        getDataByStatusUseCase.invoke(viewModelScope, params) {
            it.either(::handleDataByStatusFailure, ::handleDataByStatusSuccess)
        }
    }

    private fun handleDataByStatusFailure(failure: Failure) {
        _stateDataByStatus.value = State.Failed(failure)
    }

    private fun handleDataByStatusSuccess(dataByStatus: DataByStatus) {
        _stateDataByStatus.value = State.Success(dataByStatus.dataByStatusList.toCountryList())
    }

    private companion object {
        const val DEFAULT_STATUS = "confirmed"
    }
}
