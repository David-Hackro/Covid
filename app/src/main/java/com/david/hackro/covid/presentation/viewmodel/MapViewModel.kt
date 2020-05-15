package com.david.hackro.covid.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.hackro.domain.Failure
import com.david.hackro.domain.State
import com.david.hackro.stats.domain.model.DataByStatus
import com.david.hackro.stats.domain.usecase.GetDataByStatusUseCase

class MapViewModel(private val getDataByStatusUseCase: GetDataByStatusUseCase) : ViewModel() {

    private val _stateDataByStatus = MutableLiveData<State>()
    val stateDataByStatus: LiveData<State>
        get() = _stateDataByStatus

    fun init() {
        getDataByStatus(status = CONFIRMED)
        //getDataByStatus(status = RECOVERED)
        //getDataByStatus(status = DEATHS)
    }

    private fun getDataByStatus(status: String) {
        val params = GetDataByStatusUseCase.Params(status = status)

        getDataByStatusUseCase.invoke(viewModelScope, params) {
            it.either(::handleDataByStatusFailure, ::handleDataByStatusSuccess)
        }
    }

    private fun handleDataByStatusFailure(failure: Failure) {
        _stateDataByStatus.value = State.Failed(failure)
    }


    private fun handleDataByStatusSuccess(dataByStatus: DataByStatus) {
        _stateDataByStatus.value = State.Success(dataByStatus)
    }

    private companion object {
        const val CONFIRMED = "confirmed"
        const val RECOVERED = "recovered"
        const val DEATHS = "deaths"
    }
}
