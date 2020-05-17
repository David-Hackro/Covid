package com.david.hackro.covid.presentation.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.david.hackro.covid.R
import com.david.hackro.covid.presentation.model.TotalItem
import kotlinx.android.synthetic.main.item_total_covid.view.colorBar
import kotlinx.android.synthetic.main.item_total_covid.view.description
import kotlinx.android.synthetic.main.item_total_covid.view.rate
import kotlinx.android.synthetic.main.item_total_covid.view.rateNumber
import kotlinx.android.synthetic.main.item_total_covid.view.totalCases
import java.text.NumberFormat
import java.util.Locale

class TotalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun renderView(total: TotalItem) {
        itemView.run {
            totalCases.text = NumberFormat.getNumberInstance(Locale.US).format(total.progress)
            description.text = resources.getString(getDescription(status = total.status))
            totalCases.setTextColor(resources.getColor(getColorBar(status = total.status)))
            rateNumber.setTextColor(resources.getColor(getColorBar(status = total.status)))
            rate.setTextColor(resources.getColor(getColorBar(status = total.status)))
            background = resources.getDrawable(getBackgroundColor(total.status))

            rate.background = resources.getDrawable(getBackgroundColor(total.status))

            rateNumber.text = "%.2f".format(total.rate) + "%"



            colorBar.run {
                max = total.max
                progress = total.progress
                progressBackgroundTintList = ContextCompat.getColorStateList(itemView.context, getColorBar(status = total.status))
                progressTintList = ContextCompat.getColorStateList(itemView.context, getColorBar(status = total.status))
            }
        }
    }

    private fun getDescription(status: STATUS) = when (status) {
        STATUS.CONFIRMED -> R.string.confirmed
        STATUS.RECOVERED -> R.string.recovered
        STATUS.CRITICAL -> R.string.critical
        STATUS.DEATHS -> R.string.deaths
        STATUS.ACTIVE -> R.string.actived
    }

    private fun getColorBar(status: STATUS) = when (status) {
        STATUS.CONFIRMED -> R.color.confirmed
        STATUS.RECOVERED -> R.color.recovered
        STATUS.CRITICAL -> R.color.critical
        STATUS.DEATHS -> R.color.deaths
        STATUS.ACTIVE -> R.color.active
    }

    private fun getBackgroundColor(status: STATUS) = when (status) {
        STATUS.CONFIRMED -> R.drawable.item_background_confirmed
        STATUS.RECOVERED -> R.drawable.item_background_recovered
        STATUS.CRITICAL -> R.drawable.item_background_confirmed
        STATUS.DEATHS -> R.drawable.item_background_deaths
        STATUS.ACTIVE -> R.drawable.item_background_confirmed
    }

    private fun getBackgroundRate(status: STATUS) = when (status) {
        STATUS.CONFIRMED -> R.drawable.rate_confirmed
        STATUS.RECOVERED -> R.drawable.rate_recovered
        STATUS.CRITICAL -> R.drawable.rate_confirmed
        STATUS.DEATHS -> R.drawable.rate_deaths
        STATUS.ACTIVE -> R.drawable.rate_confirmed
    }
}

