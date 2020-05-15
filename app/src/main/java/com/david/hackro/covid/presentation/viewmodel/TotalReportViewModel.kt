package com.david.hackro.covid.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.hackro.domain.Failure
import com.david.hackro.domain.State
import com.david.hackro.domain.UseCase
import com.david.hackro.stats.domain.model.SummaryInfo
import com.david.hackro.stats.domain.usecase.GetSummaryInfoUseCase

class TotalReportViewModel(
    private val getSummaryInfoUseCase: GetSummaryInfoUseCase
) : ViewModel() {

    private val _stateSummaryInfo = MutableLiveData<State>()
    val stateSummaryInfo: LiveData<State>
        get() = _stateSummaryInfo

    fun init() {
        getTotalReport()
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
}
