package com.david.hackro.covid.presentation.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.david.hackro.covid.R
import com.david.hackro.covid.presentation.model.TotalItem
import kotlinx.android.synthetic.main.item_total_covid.view.colorBar
import kotlinx.android.synthetic.main.item_total_covid.view.description
import kotlinx.android.synthetic.main.item_total_covid.view.totalCases

class TotalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun renderView(total: TotalItem) {
        itemView.run {
            totalCases.text = total.progress.toString()
            description.text = resources.getString(getDescription(status = total.status))

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
}
