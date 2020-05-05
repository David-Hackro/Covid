package com.david.hackro.covid.presentation.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.david.hackro.covid.R
import com.david.hackro.stats.domain.model.Report
import kotlinx.android.synthetic.main.item_country_covid.view.countryColorBar
import kotlinx.android.synthetic.main.item_country_covid.view.countryName
import kotlinx.android.synthetic.main.item_country_covid.view.countryTotalNumber

class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun renderView(report: Report, onCountryItemListener: (report: Report) -> Unit) {
        renderCard(report = report)
        setListener(report = report, onCountryItemListener = onCountryItemListener)
    }

    private fun renderCard(report: Report) {
        itemView.run {

            countryName.text = report.country
            countryTotalNumber.text = report.total.confirmed.toString()

            countryColorBar.run {
                max = report.total.confirmed
                progress = report.total.active
                progressTintList = ContextCompat.getColorStateList(itemView.context, R.color.confirmed)
            }
        }
    }

    private fun setListener(report: Report, onCountryItemListener: (report: Report) -> Unit) {
        itemView.setOnClickListener { onCountryItemListener.invoke(report) }
    }
}
