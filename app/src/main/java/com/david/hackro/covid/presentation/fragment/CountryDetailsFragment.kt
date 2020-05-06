package com.david.hackro.covid.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.david.hackro.androidext.liveDataObserve
import com.david.hackro.covid.R
import com.david.hackro.covid.presentation.activity.MainActivity
import com.david.hackro.covid.presentation.adapter.TotalAdapter
import com.david.hackro.covid.presentation.model.TotalItem
import com.david.hackro.covid.presentation.model.toItemList
import com.david.hackro.covid.presentation.viewmodel.CountryDetailViewModel
import com.david.hackro.domain.State
import com.david.hackro.stats.domain.model.Report
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlinx.android.synthetic.main.fragment_country_details.totalRv
import kotlinx.android.synthetic.main.fragment_totals.pieChart
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class CountryDetailsFragment : BaseFragment() {

    private val countryDetailViewModel: CountryDetailViewModel by viewModel()
    private lateinit var totalAdapter: TotalAdapter

    override fun layoutId() = R.layout.fragment_country_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initAdapter()
        initRecycler()
        initChart()
        initValues()
    }

    private fun initObservers() {
        liveDataObserve(countryDetailViewModel.stateLatestCountryData, ::onLatestCountryDataStateChange)
    }

    private fun initAdapter() {
        totalAdapter = TotalAdapter()
    }

    private fun initRecycler() {
        totalRv.run {
            this.layoutManager = GridLayoutManager(context, SPAN_COUNT, GridLayoutManager.VERTICAL, false)
            this.adapter = totalAdapter
        }
    }

    private fun initChart() {
        pieChart.run {
            centerText = resources.getString(R.string.app_name)
            isRotationEnabled = IS_ROTATION_ENABLED
            isHighlightPerTapEnabled = IS_HIGH_LIGHT_PER_TAP_ENABLED
            animateXY(ANIMATE_DEFAULT, ANIMATE_DEFAULT)
        }
    }

    private fun initValues() {
        val args: CountryDetailsFragmentArgs by navArgs()

        countryDetailViewModel.init(name = args.code)
    }

    private fun onLatestCountryDataStateChange(state: State?) {
        state?.let { noNullState ->
            when (noNullState) {
                is State.Success -> {
                    val result = noNullState.responseTo<Report>()

                    showTotalReports(result = result)
                }
                is State.Failed -> (activity as MainActivity).handleFailure(failure = noNullState.failure)
                else -> Timber.d("any state in onLatestCountryDataStateChange")
            }
        }
    }

    private fun showTotalReports(result: Report) {
        setValuesAdapter(result.toItemList())

        val totalsCovid = arrayListOf<PieEntry>().apply {
            result.run {
                add(PieEntry(total.confirmed.toFloat(), resources.getString(R.string.confirmed)))
                add(PieEntry(total.recovered.toFloat(), resources.getString(R.string.recovered)))
                add(PieEntry(total.active.toFloat(), resources.getString(R.string.actived)))
                add(PieEntry(total.deaths.toFloat(), resources.getString(R.string.deaths)))
            }
        }

        val dataSet = PieDataSet(totalsCovid, "")

        pieChart.data = PieData(dataSet)

        resources.run {
            dataSet.setColors(
                getColor(R.color.confirmed),
                getColor(R.color.recovered),
                getColor(R.color.active),
                getColor(R.color.deaths)
            )
        }

    }

    private fun setValuesAdapter(itemList: List<TotalItem>) {
        totalAdapter.setTotalList(totalItemList = itemList)
    }

    private companion object {
        const val SPAN_COUNT = 2
        const val ANIMATE_DEFAULT = 0
        const val IS_ROTATION_ENABLED = false
        const val IS_HIGH_LIGHT_PER_TAP_ENABLED = false
    }
}
