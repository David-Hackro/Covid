package com.david.hackro.covid.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.david.hackro.androidext.liveDataObserve
import com.david.hackro.covid.R
import com.david.hackro.covid.presentation.adapter.TotalAdapter
import com.david.hackro.covid.presentation.model.TotalItem
import com.david.hackro.covid.presentation.model.toItemList
import com.david.hackro.covid.presentation.viewmodel.TotalReportViewModel
import com.david.hackro.domain.State
import com.david.hackro.stats.domain.model.Totals
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
        initChart()
        initValues()
    }

    private fun initObservers() {
        liveDataObserve(totalReportViewModel.stateTotalReport, ::onTotalReportStateChange)
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
        totalReportViewModel.init()
    }

    private fun onTotalReportStateChange(state: State?) {
        state?.let { noNullState ->
            when (noNullState) {
                is State.Loading -> getActivityContext().showProgress()
                is State.Success -> {
                    val result = noNullState.responseTo<Totals>()

                    getActivityContext().hideProgress()

                    showTotalReports(result = result)
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

    private fun showTotalReports(result: Totals) {
        setValuesAdapter(result.toItemList())

        val totalsCovid = arrayListOf<PieEntry>().apply {
            result.run {
                add(PieEntry(confirmed.toFloat()))
                add(PieEntry(recovered.toFloat()))
                add(PieEntry(critical.toFloat()))
                add(PieEntry(deaths.toFloat()))
            }
        }

        val dataSet = PieDataSet(totalsCovid, "")

        pieChart.data = PieData(dataSet)

        resources.run {
            dataSet.setColors(
                getColor(R.color.confirmed),
                getColor(R.color.recovered),
                getColor(R.color.critical),
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
        const val IS_HIGH_LIGHT_PER_TAP_ENABLED = true
    }
}
