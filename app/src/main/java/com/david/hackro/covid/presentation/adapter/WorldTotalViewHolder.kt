package com.david.hackro.covid.presentation.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.david.hackro.covid.R
import com.david.hackro.covid.presentation.model.WorldTotalItem
import kotlinx.android.synthetic.main.item_world_total.view.colorBar
import kotlinx.android.synthetic.main.item_world_total.view.description
import kotlinx.android.synthetic.main.item_world_total.view.rate
import kotlinx.android.synthetic.main.item_world_total.view.rateNumber
import kotlinx.android.synthetic.main.item_world_total.view.totalCases
import java.text.NumberFormat
import java.util.Locale

class WorldTotalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun renderView(worldTotal: WorldTotalItem) {
        itemView.run {
            totalCases.text = NumberFormat.getNumberInstance(Locale.US).format(worldTotal.progress)
            description.text = resources.getString(getDescription(status = worldTotal.status))
            totalCases.setTextColor(resources.getColor(getColorBar(status = worldTotal.status)))
            rateNumber.setTextColor(resources.getColor(getColorBar(status = worldTotal.status)))
            rate.setTextColor(resources.getColor(getColorBar(status = worldTotal.status)))
            background = resources.getDrawable(getBackgroundColor(worldTotal.status))

            rateNumber.text = "%.2f".format(worldTotal.rate) + "%"

            colorBar.run {
                max = worldTotal.max
                progress = worldTotal.progress
                progressBackgroundTintList = ContextCompat.getColorStateList(itemView.context, getColorBar(status = worldTotal.status))
                progressTintList = ContextCompat.getColorStateList(itemView.context, getColorBar(status = worldTotal.status))
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

}

