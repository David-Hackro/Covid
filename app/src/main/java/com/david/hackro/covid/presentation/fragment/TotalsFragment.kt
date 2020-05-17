package com.david.hackro.covid.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.david.hackro.androidext.liveDataObserve
import com.david.hackro.covid.R
import com.david.hackro.covid.presentation.adapter.CountryAdapter
import com.david.hackro.covid.presentation.adapter.TotalAdapter
import com.david.hackro.covid.presentation.model.CountryItem
import com.david.hackro.covid.presentation.model.TotalItem
import com.david.hackro.covid.presentation.model.toItemList
import com.david.hackro.covid.presentation.viewmodel.TotalReportViewModel
import com.david.hackro.domain.State
import com.david.hackro.stats.domain.model.SummaryInfo
import kotlinx.android.synthetic.main.fragment_totals.rvCountry
import kotlinx.android.synthetic.main.fragment_totals.rvTotal
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class TotalsFragment : BaseFragment() {

    private val totalReportViewModel: TotalReportViewModel by viewModel()

    private lateinit var totalAdapter: TotalAdapter
    private lateinit var countryAdapter: CountryAdapter

    override fun layoutId() = R.layout.fragment_totals

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initAdapter()
        initListener()
        initRecycler()
        initValues()
    }

    private fun initObservers() {
        liveDataObserve(totalReportViewModel.stateSummaryInfo, ::onSummaryInfoStateChange)
        liveDataObserve(totalReportViewModel.stateCountryData, ::onCountryDataStateChange)
    }

    private fun initAdapter() {
        totalAdapter = TotalAdapter()
        countryAdapter = CountryAdapter()
    }


    private fun initListener() {
        countryAdapter.onCountryItemListener = {

        }
    }

    private fun initRecycler() {
        rvTotal.run {
            this.layoutManager = GridLayoutManager(context, SPAN_COUNT, GridLayoutManager.VERTICAL, false)
            this.adapter = totalAdapter
        }

        rvCountry.run {
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this.adapter = countryAdapter
        }
    }

    private fun initValues() {
        totalReportViewModel.init()
    }

    private fun onSummaryInfoStateChange(state: State?) {
        state?.let { noNullState ->
            when (noNullState) {
                is State.Loading -> getActivityContext().showProgress()
                is State.Success -> {
                    val result = noNullState.responseTo<SummaryInfo>()

                    getActivityContext().hideProgress()

                    showTotalReports(summaryInfo = result)
                }
                is State.Failed -> {
                    getActivityContext().run {
                        hideProgress()
                        handleFailure(failure = noNullState.failure)
                    }
                }
                else -> Timber.d("any state in onTotalReportStateChange")
            }
        }
    }

    private fun onCountryDataStateChange(state: State?) {
        state?.let { noNullState ->
            when (noNullState) {
                is State.Loading -> getActivityContext().showProgress()
                is State.Success -> {
                    val result = noNullState.responseTo<List<CountryItem>>()

                    getActivityContext().hideProgress()

                    showCountryList(countryList = result)
                }
                is State.Failed -> {
                    getActivityContext().run {
                        hideProgress()
                        handleFailure(failure = noNullState.failure)
                    }
                }
                else -> Timber.d("any state in onTotalReportStateChange")
            }
        }
    }

    private fun showCountryList(countryList: List<CountryItem>) {
        countryAdapter.setCountryList(countryList = countryList)
    }

    private fun showTotalReports(summaryInfo: SummaryInfo) {
        setValuesAdapter(summaryInfo.toItemList())
    }

    private fun setValuesAdapter(itemList: List<TotalItem>) {
        totalAdapter.setTotalList(totalItemList = itemList)
    }

    private companion object {
        const val SPAN_COUNT = 2
    }
}
