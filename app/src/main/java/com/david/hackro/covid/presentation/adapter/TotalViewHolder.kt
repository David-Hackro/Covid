package com.david.hackro.covid.presentation.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.david.hackro.covid.R
import kotlinx.android.synthetic.main.item_total_covid.view.colorBar
import kotlinx.android.synthetic.main.item_total_covid.view.description
import kotlinx.android.synthetic.main.item_total_covid.view.totalCases

class TotalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun renderView(total: Int, status: STATUS) {
        itemView.run {
            totalCases.text = total.toString()
            description.text = resources.getString(getDescription(status = status))

            colorBar.run {
                max = 3438320
                progress = total
                progressBackgroundTintList = ContextCompat.getColorStateList(itemView.context, getColorBar(status = status))
                progressTintList = ContextCompat.getColorStateList(itemView.context, getColorBar(status = status))
            }

        }
    }

    private fun getDescription(status: STATUS) = when (status) {
        STATUS.CONFIRMED -> R.string.confirmed
        STATUS.RECOVERED -> R.string.recovered
        STATUS.CRITICAL -> R.string.critical
        STATUS.DEATHS -> R.string.deaths
    }

    private fun getColorBar(status: STATUS) = when (status) {
        STATUS.CONFIRMED -> R.color.confirmed
        STATUS.RECOVERED -> R.color.recovered
        STATUS.CRITICAL -> R.color.critical
        STATUS.DEATHS -> R.color.deaths
    }
}
