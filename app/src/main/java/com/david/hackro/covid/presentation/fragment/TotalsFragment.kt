package com.david.hackro.covid.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.david.hackro.androidext.liveDataObserve
import com.david.hackro.covid.R
import com.david.hackro.covid.presentation.adapter.TotalAdapter
import com.david.hackro.covid.presentation.viewmodel.TotalReportViewModel
import com.david.hackro.domain.State
import com.david.hackro.stats.domain.model.Totals
import com.david.hackro.stats.domain.model.toItemList
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlinx.android.synthetic.main.fragment_totals.pieChart
import kotlinx.android.synthetic.main.fragment_totals.totalRv
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class TotalsFragment : BaseFragment() {

    private val totalReportViewModel: TotalReportViewModel by viewModel()

    private lateinit var totalAdapter: TotalAdapter

    override fun layoutId() = R.layout.fragment_totals

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initAdapter()
        initRecycler()

        totalReportViewModel.init()
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

    private fun initObservers() {
        liveDataObserve(totalReportViewModel.stateTotalReport, ::onTotalReportStateChange)
    }

    private fun onTotalReportStateChange(state: State?) {
        state?.let { noNullState ->
            when (noNullState) {
                is State.Success -> {

                    val result = noNullState.responseTo<List<Totals>>()

                    showTotalReports(resultList = result)
                }
                else -> Timber.d("any state in onTotalReportStateChange")
            }
        }
    }

    private fun showTotalReports(resultList: List<Totals>) {
        totalAdapter.setTotalList(totalItemList = resultList.first().toItemList())

        val totalsCovid = arrayListOf<PieEntry>().apply {
                resultList.first().run {
                    add(PieEntry(confirmed.toFloat(), resources.getString(R.string.confirmed)))
                    add(PieEntry(recovered.toFloat(), resources.getString(R.string.recovered)))
                    add(PieEntry(critical.toFloat(), resources.getString(R.string.critical)))
                    add(PieEntry(deaths.toFloat(), resources.getString(R.string.deaths)))
                }
        }

        val dataSet = PieDataSet(totalsCovid, "")

        val data = PieData(dataSet)
        pieChart.data = data

        resources.run {
            dataSet.setColors(
                getColor(R.color.confirmed),
                getColor(R.color.recovered),
                getColor(R.color.critical),
                getColor(R.color.deaths)
            )
        }

        pieChart.run {
            centerText = resources.getString(R.string.app_name)
            isRotationEnabled = false
            isHighlightPerTapEnabled = true
            animateXY(ANIMATE_DEFAULT, ANIMATE_DEFAULT)
        }

    }

    private companion object {
        const val SPAN_COUNT = 2
        const val ANIMATE_DEFAULT = 0
    }
}
