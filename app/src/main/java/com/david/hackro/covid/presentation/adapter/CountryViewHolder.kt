package com.david.hackro.covid.presentation.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.david.hackro.covid.R
import com.david.hackro.stats.domain.model.CountryItem
import kotlinx.android.synthetic.main.item_country_covid.view.countryColorBar
import kotlinx.android.synthetic.main.item_country_covid.view.countryName
import kotlinx.android.synthetic.main.item_country_covid.view.countryTotalNumber

class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun renderView(countryItem: CountryItem, onCountryItemListener: (countryItem: CountryItem) -> Unit) {
        renderCard(countryItem = countryItem)
        setListener(countryItem = countryItem, onCountryItemListener = onCountryItemListener)
    }

    private fun renderCard(countryItem: CountryItem) {
        itemView.run {

            countryName.text = countryItem.countryName
            countryTotalNumber.text = countryItem.confirmed.toString()

            countryColorBar.run {
                max = countryItem.confirmed
                progress = countryItem.active
                progressTintList = ContextCompat.getColorStateList(itemView.context, R.color.confirmed)
            }
        }
    }

    private fun setListener(countryItem: CountryItem, onCountryItemListener: (countryItem: CountryItem) -> Unit) {
        itemView.setOnClickListener { onCountryItemListener.invoke(countryItem) }
    }
}
