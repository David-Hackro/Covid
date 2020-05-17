package com.david.hackro.covid.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.david.hackro.covid.R
import com.david.hackro.covid.presentation.model.CountryTotalItem
import kotlinx.android.synthetic.main.item_country_total.view.totalLabel
import kotlinx.android.synthetic.main.item_country_total.view.totalNumber
import kotlinx.android.synthetic.main.item_country_total.view.total_icon
import java.text.NumberFormat
import java.util.Locale

class CountryTotalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun renderView(worldTotal: CountryTotalItem) {
        itemView.run {
            totalNumber.text = NumberFormat.getNumberInstance(Locale.US).format(worldTotal.progress)
            totalNumber.setTextColor(resources.getColor(getColorBar(status = worldTotal.status)))
            totalLabel.text = resources.getString(getDescription(status = worldTotal.status))
            total_icon.background = resources.getDrawable(getIcon(status = worldTotal.status))
            background = resources.getDrawable(getBackgroundColor(worldTotal.status))

        }
    }

    private fun getIcon(status: STATUS) = when (status) {
        STATUS.CONFIRMED -> R.drawable.ic_confirmed
        STATUS.RECOVERED -> R.drawable.ic_heart
        STATUS.DEATHS -> R.drawable.ic_death
        STATUS.ACTIVE -> R.drawable.ic_active
        else -> R.drawable.ic_confirmed
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
        STATUS.DEATHS -> R.drawable.item_background_deaths
        STATUS.ACTIVE -> R.drawable.item_background_active
        else -> R.drawable.item_background_confirmed
    }

}

