package com.david.hackro.covid.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.hackro.domain.Failure
import com.david.hackro.domain.State
import com.david.hackro.kotlinext.yesterday
import com.david.hackro.stats.domain.model.Report
import com.david.hackro.stats.domain.usecase.GetLatestCountryDataByNameUseCase

class CountryDetailViewModel(private val getLatestCountryDataByCodeUseCase: GetLatestCountryDataByNameUseCase) : ViewModel() {

    private val _stateLatestCountryData = MutableLiveData<State>()
    val stateLatestCountryData: LiveData<State>
        get() = _stateLatestCountryData

    fun init(name: String) {
        getLatestCountryData(name = name)
    }

    private fun getLatestCountryData(name: String) {
        _stateLatestCountryData.value = State.Loading

        val params = GetLatestCountryDataByNameUseCase.Params(name = name, date = yesterday(DATE_FORMAT))

        getLatestCountryDataByCodeUseCase.invoke(viewModelScope, params) {
            it.either(::handleLatestCountryDataByCodeFailure, ::handleLatestCountryDataByCodeSuccess)
        }
    }

    private fun handleLatestCountryDataByCodeFailure(failure: Failure) {
        _stateLatestCountryData.value = State.Failed(failure)
    }

    private fun handleLatestCountryDataByCodeSuccess(list: List<Report>) {
        _stateLatestCountryData.value = State.Success(list.first())
    }

    private companion object {
        const val DATE_FORMAT = "YYYY-MM-DD"
    }
}
