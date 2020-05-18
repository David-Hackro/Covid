package com.david.hackro.covid.presentation.adapter.wordltotal

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.david.hackro.covid.presentation.model.WorldTotalItem
import com.david.hackro.covid.presentation.model.getBackgroundByStatus
import com.david.hackro.covid.presentation.model.getColorByStatus
import com.david.hackro.covid.presentation.model.getDescriptionByStatus
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

            totalCases.text = getTotal(worldTotal.progress)

            description.text = resources.getString(worldTotal.status.getDescriptionByStatus())
            totalCases.setTextColor(resources.getColor(worldTotal.status.getColorByStatus()))
            rateNumber.setTextColor(resources.getColor(worldTotal.status.getColorByStatus()))
            rate.setTextColor(resources.getColor(worldTotal.status.getColorByStatus()))
            background = resources.getDrawable(worldTotal.status.getBackgroundByStatus())

            rateNumber.text = TWO_FLOAT.format(worldTotal.rate) + PERCENTAGE

            colorBar.run {
                max = worldTotal.max
                progress = worldTotal.progress
                progressBackgroundTintList = ContextCompat.getColorStateList(itemView.context, worldTotal.status.getColorByStatus())
                progressTintList = ContextCompat.getColorStateList(itemView.context, worldTotal.status.getColorByStatus())
            }
        }
    }

    private fun getTotal(progress: Int) = if (progress > TOTAL_EMPTY) NumberFormat.getNumberInstance(Locale.US).format(progress)
    else TOTAL_EMPTY_DEFAULT

    private companion object {
        const val TWO_FLOAT = "%.2f"
        const val PERCENTAGE = "%"
        const val TOTAL_EMPTY_DEFAULT = "- -"
        const val TOTAL_EMPTY = 0
    }
}

