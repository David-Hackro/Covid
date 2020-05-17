package com.david.hackro.covid.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.hackro.covid.presentation.model.toCountryList
import com.david.hackro.domain.Failure
import com.david.hackro.domain.State
import com.david.hackro.stats.domain.model.DataByStatus
import com.david.hackro.stats.domain.usecase.GetDataByStatusUseCase

class CountryDetailViewModel(private val getDataByStatusUseCase: GetDataByStatusUseCase) : ViewModel() {

    lateinit var countryIso: String

    private val _stateCountryDetail = MutableLiveData<State>()
    val stateCountryDetail: LiveData<State>
        get() = _stateCountryDetail

    fun init(countryIso: String) {
        this.countryIso = countryIso

        getDataByStatus(status = DEFAULT_STATUS)
    }

    private fun getDataByStatus(status: String) {
        val params = GetDataByStatusUseCase.Params(status = status)

        getDataByStatusUseCase.invoke(viewModelScope, params) {
            it.either(::handleDataByStatusFailure, ::handleDataByStatusSuccess)
        }
    }

    private fun handleDataByStatusSuccess(dataByStatus: DataByStatus) {
        val countryList = dataByStatus.dataByStatusList.toCountryList()

        _stateCountryDetail.value = State.Success(countryList.first { it.countryIso1 == countryIso })
    }

    private fun handleDataByStatusFailure(failure: Failure) {
        _stateCountryDetail.value = State.Failed(failure)
    }

    private companion object {
        const val DEFAULT_STATUS = "confirmed"
    }
}
