package com.david.hackro.covid.presentation.adapter.country

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.david.hackro.androidext.setUrlCircle
import com.david.hackro.covid.R
import com.david.hackro.covid.presentation.model.CountryItem
import com.david.hackro.kotlinext.formatValue
import kotlinx.android.synthetic.main.item_country_covid.view.activeNumber
import kotlinx.android.synthetic.main.item_country_covid.view.confirmedNumber
import kotlinx.android.synthetic.main.item_country_covid.view.deathsNumber
import kotlinx.android.synthetic.main.item_country_covid.view.flag
import kotlinx.android.synthetic.main.item_country_covid.view.recoveredNumber

class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun renderView(countryItem: CountryItem, onCountryItemListener: (countryItem: CountryItem) -> Unit) {
        renderCard(countryItem = countryItem)
        setListener(countryItem = countryItem, onCountryItemListener = onCountryItemListener)
    }

    private fun renderCard(countryItem: CountryItem) {
        itemView.run {
            confirmedNumber.text = countryItem.confirmed.toDouble().formatValue()
            deathsNumber.text = countryItem.death.toDouble().formatValue()
            recoveredNumber.text = countryItem.recovered.toDouble().formatValue()
            activeNumber.text = countryItem.active.toDouble().formatValue()
            flag.setUrlCircle(String.format(resources.getString(R.string.url_flag), countryItem.countryIso1.toLowerCase()))
        }
    }

    private fun setListener(countryItem: CountryItem, onCountryItemListener: (countryItem: CountryItem) -> Unit) {
        itemView.setOnClickListener { onCountryItemListener.invoke(countryItem) }
    }
}
