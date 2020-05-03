package com.david.hackro.covid.presentation.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.david.hackro.covid.R
import com.david.hackro.stats.domain.model.Report
import kotlinx.android.synthetic.main.item_country_covid.view.countryColorBar
import kotlinx.android.synthetic.main.item_country_covid.view.countryName
import kotlinx.android.synthetic.main.item_country_covid.view.countryTotal

class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun renderView(report: Report) {
        itemView.run {
            countryName.text = report.country
            countryTotal.text = report.total.confirmed.toString()

            countryColorBar.run {
                max = report.total.confirmed
                progress = report.total.active
                progressTintList = ContextCompat.getColorStateList(itemView.context, R.color.confirmed)
            }
        }
    }

}
