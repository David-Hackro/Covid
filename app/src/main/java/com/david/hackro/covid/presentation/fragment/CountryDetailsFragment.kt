package com.david.hackro.covid.presentation.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.david.hackro.androidext.liveDataObserve
import com.david.hackro.androidext.setUrlCircle
import com.david.hackro.covid.R
import com.david.hackro.covid.presentation.adapter.CountryTotalAdapter
import com.david.hackro.covid.presentation.model.CountryItem
import com.david.hackro.covid.presentation.model.CountryTotalItem
import com.david.hackro.covid.presentation.model.toItemList
import com.david.hackro.covid.presentation.viewmodel.CountryDetailViewModel
import com.david.hackro.domain.State
import com.david.hackro.kotlinext.empty
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
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
        val barDataSet = BarDataSet(getData(countryItem = countryItem), String.empty())

        barDataSet.colors = mutableListOf(
            resources.getColor(R.color.confirmed),
            resources.getColor(R.color.deaths),
            resources.getColor(R.color.recovered),
            resources.getColor(R.color.active)
        )

        val barData = BarData(barDataSet)
        val xAxis: XAxis = horizontalBarChart.xAxis

        xAxis.run {
            position = XAxis.XAxisPosition.BOTTOM
            granularity = GRANULARITY
        }

        horizontalBarChart.run {
            data = barData
            setFitBars(true)
            description.text = String.empty()
            axisLeft.setDrawLabels(DRAW_LABELS_DISABLE)
            xAxis.setDrawLabels(DRAW_LABELS_DISABLE)
            legend.isEnabled = LEGEND_DISABLE
            animateXY(ANIMATE_DEFAULT, ANIMATE_DEFAULT)
            invalidate()
        }
    }


    private fun getData(countryItem: CountryItem): ArrayList<BarEntry> {
        val entries = ArrayList<BarEntry>()

        countryItem.run {
            entries.add(BarEntry(ENTRY_CONFIRMED, confirmed.toFloat()))
            entries.add(BarEntry(ENTRY_DEATH, death.toFloat()))
            entries.add(BarEntry(ENTRY_RECOVERED, recovered.toFloat()))
            entries.add(BarEntry(ENTRY_ACTIVE, active.toFloat()))
        }

        return entries
    }

    private fun initValues() {
        val args: CountryDetailsFragmentArgs by navArgs()
        countryDetailViewModel.init(countryIso = args.countryIso)
        flag.setUrlCircle(String.format(resources.getString(R.string.url_flag), args.countryIso.toLowerCase()))
        country.text = args.countryIso
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
        country.text = countryItem.countryName
        initChart(countryItem = countryItem)
        showTotalByCountry(countryTotalItemList = countryItem.toItemList())
    }

    private fun showTotalByCountry(countryTotalItemList: List<CountryTotalItem>) {
        countryTotalAdapter.setCountryItemList(countryTotalItemList = countryTotalItemList)
    }

    private companion object {
        const val SPAN_COUNT = 2
        const val ANIMATE_DEFAULT = 1500
        const val LEGEND_DISABLE = false
        const val DRAW_LABELS_DISABLE = false
        const val ENTRY_CONFIRMED = 3F
        const val ENTRY_DEATH = 2F
        const val ENTRY_RECOVERED = 1F
        const val ENTRY_ACTIVE = 0F
        const val REVERSE_LAYOUT = false
        const val GRANULARITY = 1F
    }
}
