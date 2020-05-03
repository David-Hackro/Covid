package com.david.hackro.covid.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.david.hackro.androidext.liveDataObserve
import com.david.hackro.covid.R
import com.david.hackro.covid.presentation.adapter.CountryAdapter
import com.david.hackro.covid.presentation.viewmodel.DailyReportAllCountriesViewModel
import com.david.hackro.domain.State
import com.david.hackro.stats.domain.model.Report
import kotlinx.android.synthetic.main.fragment_countries.countryRv
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class CountriesFragment : BaseFragment() {

    private val dailyReportAllCountriesViewModel: DailyReportAllCountriesViewModel by viewModel()
    private lateinit var countryAdapter: CountryAdapter

    override fun layoutId() = R.layout.fragment_countries

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initAdapter()
        initRecycler()
        initValues()
    }

    private fun initObservers() {
        liveDataObserve(dailyReportAllCountriesViewModel.stateDailyReport, ::onDailyReportStateChange)
    }

    private fun initAdapter() {
        countryAdapter = CountryAdapter()
    }

    private fun initRecycler() {
        countryRv.run {
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
            this.adapter = countryAdapter
        }
    }

    private fun initValues() {
        dailyReportAllCountriesViewModel.init()
    }

    private fun onDailyReportStateChange(state: State?) {
        state?.let { noNullState ->
            when (noNullState) {
                is State.Success -> {

                    val result = noNullState.responseTo<List<Report>>()

                    showDailyReports(resultList = result)
                }
                else -> Timber.d("any state in onTotalReportStateChange")
            }
        }
    }

    private fun showDailyReports(resultList: List<Report>) {
        countryAdapter.setCountryList(resultList)
    }
}
