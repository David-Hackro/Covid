package com.david.hackro.covid.presentation.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.david.hackro.androidext.liveDataObserve
import com.david.hackro.androidext.setUrlCircle
import com.david.hackro.covid.R
import com.david.hackro.covid.presentation.adapter.countrytotal.CountryTotalAdapter
import com.david.hackro.covid.presentation.model.CountryItem
import com.david.hackro.covid.presentation.model.CountryTotalItem
import com.david.hackro.covid.presentation.model.toEntryList
import com.david.hackro.covid.presentation.model.toItemList
import com.david.hackro.covid.presentation.viewmodel.CountryDetailViewModel
import com.david.hackro.domain.State
import com.david.hackro.kotlinext.empty
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import kotlinx.android.synthetic.main.fragment_country_details.bannerTips
import kotlinx.android.synthetic.main.fragment_country_details.country
import kotlinx.android.synthetic.main.fragment_country_details.flag
import kotlinx.android.synthetic.main.fragment_country_details.horizontalBarChart
import kotlinx.android.synthetic.main.fragment_country_details.rvCountry
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class CountryDetailsFragment : BaseFragment() {

    private val countryDetailViewModel: CountryDetailViewModel by viewModel()
    private lateinit var countryTotalAdapter: CountryTotalAdapter

    override fun layoutId() = R.layout.fragment_country_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initAdapter()
        initRecycler()
        initListener()
        initValues()
    }

    private fun initObservers() {
        liveDataObserve(countryDetailViewModel.stateCountryDetail, ::onLatestCountryDataStateChange)
    }

    private fun initAdapter() {
        countryTotalAdapter = CountryTotalAdapter()
    }

    private fun initRecycler() {
        rvCountry.run {
            this.layoutManager = GridLayoutManager(context, SPAN_COUNT, GridLayoutManager.VERTICAL, REVERSE_LAYOUT)
            this.adapter = countryTotalAdapter
        }
    }

    private fun initListener() {
        bannerTips.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(resources.getString(R.string.url_questions_oms)))
            startActivity(browserIntent)
        }
    }

    private fun initChart(countryItem: CountryItem) {
        val barDataSet = BarDataSet(countryItem.toEntryList(), String.empty())

        barDataSet.colors = getCharColors()

        val barData = BarData(barDataSet)

        val xAxis: XAxis = horizontalBarChart.xAxis

        xAxis.run {
            position = XAxis.XAxisPosition.BOTTOM
            granularity = GRANULARITY
            setDrawLabels(DRAW_LABELS_DISABLE)
        }

        horizontalBarChart.run {
            data = barData
            setFitBars(true)
            description.text = String.empty()
            axisLeft.setDrawLabels(DRAW_LABELS_DISABLE)
            legend.isEnabled = LEGEND_DISABLE
            animateXY(ANIMATE_DEFAULT_X, ANIMATE_DEFAULT_Y)
            invalidate()
        }
    }

    private fun getCharColors() = mutableListOf(
        ContextCompat.getColor(getActivityContext(), R.color.confirmed),
        ContextCompat.getColor(getActivityContext(), R.color.deaths),
        ContextCompat.getColor(getActivityContext(), R.color.recovered),
        ContextCompat.getColor(getActivityContext(), R.color.active)
    )

    private fun initValues() {
        arguments?.getString(ARG_PARAM)?.run {
            setModelViewValue(this)
            showFlag(countryIso = this)
            showNameCountry(countryIso = this)
        }
    }

    private fun setModelViewValue(countryIso: String) {
        countryDetailViewModel.init(countryIso = countryIso)
    }

    private fun showNameCountry(countryIso: String) {
        country.text = countryIso
    }

    private fun showFlag(countryIso: String) {
        flag.setUrlCircle(String.format(resources.getString(R.string.url_flag), countryIso.toLowerCase()))
    }

    private fun onLatestCountryDataStateChange(state: State?) {
        state?.let { noNullState ->
            when (noNullState) {
                is State.Loading -> getActivityContext().showProgress()
                is State.Success -> {
                    val result = noNullState.responseTo<CountryItem>()

                    getActivityContext().hideProgress()

                    showDataByCountry(countryItem = result)
                }
                is State.Failed -> {
                    getActivityContext().run {
                        hideProgress()
                        handleFailure(failure = noNullState.failure)
                    }
                }
                else -> Timber.d("any state in onLatestCountryDataStateChange")
            }
        }
    }

    private fun showDataByCountry(countryItem: CountryItem) {
        showNameCountry(countryIso = countryItem.countryName)
        initChart(countryItem = countryItem)
        showTotalByCountry(countryTotalItemList = countryItem.toItemList())
    }

    private fun showTotalByCountry(countryTotalItemList: List<CountryTotalItem>) {
        countryTotalAdapter.setCountryItemList(countryTotalItemList = countryTotalItemList)
    }

    companion object {

        fun getInstance(countryIso1: String): Fragment {
            val args = Bundle().apply { putString(ARG_PARAM, countryIso1) }
            return CountryDetailsFragment().apply { arguments = args }
        }

        private const val ARG_PARAM = "countryIso"
        private const val SPAN_COUNT = 2
        private const val ANIMATE_DEFAULT_X = 1500
        private const val ANIMATE_DEFAULT_Y = 2500
        private const val LEGEND_DISABLE = false
        private const val DRAW_LABELS_DISABLE = false
        private const val REVERSE_LAYOUT = false
        private const val GRANULARITY = 1F
    }
}
