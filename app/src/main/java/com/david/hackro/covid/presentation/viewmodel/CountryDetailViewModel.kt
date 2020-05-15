package com.david.hackro.covid.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.david.hackro.domain.State

class CountryDetailViewModel : ViewModel() {

    private val _stateLatestCountryData = MutableLiveData<State>()
    val stateLatestCountryData: LiveData<State>
        get() = _stateLatestCountryData

    fun init(name: String) {
        getLatestCountryData(name = name)
    }

    private fun getLatestCountryData(name: String) {
        _stateLatestCountryData.value = State.Loading

    }

    private companion object {
        const val DATE_FORMAT = "yyyy-MM-dd"
    }
}
