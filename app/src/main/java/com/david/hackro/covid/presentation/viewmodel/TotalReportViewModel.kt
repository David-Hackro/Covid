package com.david.hackro.covid.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.hackro.domain.Failure
import com.david.hackro.domain.State
import com.david.hackro.domain.UseCase
import com.david.hackro.stats.domain.model.Totals
import com.david.hackro.stats.domain.usecase.GetLatestTotalsUseCase

class TotalReportViewModel(private val getLatestTotalsUseCase: GetLatestTotalsUseCase): ViewModel() {

    private val _stateTotalReport = MutableLiveData<State>()
    val stateTotalReport: LiveData<State>
        get() = _stateTotalReport

    fun init() {
        getTotalReport()
    }

    private fun getTotalReport() {
        _stateTotalReport.value = State.Loading

        getLatestTotalsUseCase.invoke(viewModelScope, UseCase.None()) {
            it.either(::handleTotalReportFailure, ::handleTotalReportSuccess)
        }
    }

    private fun handleTotalReportFailure(failure: Failure) {
        _stateTotalReport.value = State.Failed(failure)
    }

    private fun handleTotalReportSuccess(totals: List<Totals>) {
        _stateTotalReport.value = State.Success(totals)
    }
}
