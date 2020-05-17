package com.david.hackro.covid.presentation.adapter

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
            totalCases.text = NumberFormat.getNumberInstance(Locale.US).format(worldTotal.progress)
            description.text = resources.getString(worldTotal.status.getDescriptionByStatus())
            totalCases.setTextColor(resources.getColor(worldTotal.status.getColorByStatus()))
            rateNumber.setTextColor(resources.getColor(worldTotal.status.getColorByStatus()))
            rate.setTextColor(resources.getColor(worldTotal.status.getColorByStatus()))
            background = resources.getDrawable(worldTotal.status.getBackgroundByStatus())

            rateNumber.text = "%.2f".format(worldTotal.rate) + "%"

            colorBar.run {
                max = worldTotal.max
                progress = worldTotal.progress
                progressBackgroundTintList = ContextCompat.getColorStateList(itemView.context, worldTotal.status.getColorByStatus())
                progressTintList = ContextCompat.getColorStateList(itemView.context, worldTotal.status.getColorByStatus())
            }
        }
    }
}

