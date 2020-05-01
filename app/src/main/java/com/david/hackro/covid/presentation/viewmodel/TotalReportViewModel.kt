package com.david.hackro.covid.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.hackro.covid.State
import com.david.hackro.domain.Failure
import com.david.hackro.kotlinext.getCurrentDateTime
import com.david.hackro.kotlinext.toString
import com.david.hackro.stats.domain.model.TotalReport
import com.david.hackro.stats.domain.usecase.GetTotalReportByDateUseCase

class TotalReportViewModel(private val getTotalReportByDateUseCase: GetTotalReportByDateUseCase) : ViewModel() {

    private val _stateTotalReport = MutableLiveData<State>()
    val stateTotalReport: LiveData<State>
        get() = _stateTotalReport

    private fun getTotalReport() {
        _stateTotalReport.value = State.Loading

        val date = getCurrentDateTime()

        val params = GetTotalReportByDateUseCase.Params(date = date.toString(DATE_FORMAT))

        getTotalReportByDateUseCase.invoke(viewModelScope, params) {
            it.either(::handleTotalReportFailure, ::handleTotalReportSuccess)
        }
    }

    private fun handleTotalReportFailure(failure: Failure) {
        _stateTotalReport.value = State.Failed(failure)
    }

    private fun handleTotalReportSuccess(totalReport: TotalReport) {
        _stateTotalReport.value = State.Success(totalReport)
    }

    private companion object {
        const val DATE_FORMAT = "yyyy-MM-dd"
    }
}
