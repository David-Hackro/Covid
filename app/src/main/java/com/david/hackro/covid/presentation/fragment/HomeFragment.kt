package com.david.hackro.covid.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.david.hackro.androidext.liveDataObserve
import com.david.hackro.covid.R
import com.david.hackro.covid.presentation.adapter.country.CountryAdapter
import com.david.hackro.covid.presentation.adapter.wordltotal.WorldTotalAdapter
import com.david.hackro.covid.presentation.model.CountryItem
import com.david.hackro.covid.presentation.model.WorldTotalItem
import com.david.hackro.covid.presentation.model.toItemList
import com.david.hackro.covid.presentation.viewmodel.HomeViewModel
import com.david.hackro.domain.State
import com.david.hackro.stats.domain.model.SummaryInfo
import kotlinx.android.synthetic.main.fragment_home.rvCountry
import kotlinx.android.synthetic.main.fragment_home.rvTotal
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : BaseFragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private lateinit var worldTotalAdapter: WorldTotalAdapter
    private lateinit var countryAdapter: CountryAdapter

    override fun layoutId() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initAdapter()
        initListener()
        initRecycler()
        initValues()
    }

    private fun initObservers() {
        liveDataObserve(homeViewModel.stateSummaryInfo, ::onSummaryInfoStateChange)
        liveDataObserve(homeViewModel.stateCountryData, ::onCountryDataStateChange)
    }

    private fun initAdapter() {
        worldTotalAdapter = WorldTotalAdapter()
        countryAdapter = CountryAdapter()
    }

    private fun initListener() {
        countryAdapter.onCountryItemListener = {
            goToCountryDetail(countryItem = it)
        }
    }

    private fun goToCountryDetail(countryItem: CountryItem) {
        getActivityContext().addFragment(CountryDetailsFragment.getInstance(countryIso1 = countryItem.countryIso1))
    }

    private fun initRecycler() {
        rvTotal.run {
            this.layoutManager = GridLayoutManager(context, SPAN_COUNT, GridLayoutManager.VERTICAL, REVERSE_LAYOUT)
            this.adapter = worldTotalAdapter
        }

        rvCountry.run {
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, REVERSE_LAYOUT)
            this.adapter = countryAdapter
        }
    }

    private fun initValues() {
        homeViewModel.init()
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

    private fun setValuesAdapter(worldTotalItemList: List<WorldTotalItem>) {
        worldTotalAdapter.setTotalList(worldTotalItemList = worldTotalItemList)
    }

    companion object {
        fun getInstance() = HomeFragment()

        private const val SPAN_COUNT = 2
        private const val REVERSE_LAYOUT = false
    }
}
