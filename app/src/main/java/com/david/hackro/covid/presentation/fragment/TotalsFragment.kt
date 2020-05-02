package com.david.hackro.covid.presentation.fragment

import android.os.Bundle
import android.view.View
import com.david.hackro.covid.R
import com.david.hackro.covid.presentation.viewmodel.TotalReportViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TotalsFragment : BaseFragment() {

    private val totalReportViewModel: TotalReportViewModel by viewModel()

    override fun layoutId() = R.layout.fragment_totals

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        totalReportViewModel.init()
    }

}
