package com.david.hackro.covid.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.david.hackro.androidext.setUrl
import com.david.hackro.androidext.setUrlCircle
import com.david.hackro.covid.presentation.model.CountryItem
import com.david.hackro.kotlinext.formatValue
import kotlinx.android.synthetic.main.item_country_covid.view.active
import kotlinx.android.synthetic.main.item_country_covid.view.activeNumber
import kotlinx.android.synthetic.main.item_country_covid.view.confirmed
import kotlinx.android.synthetic.main.item_country_covid.view.confirmedNumber
import kotlinx.android.synthetic.main.item_country_covid.view.deaths
import kotlinx.android.synthetic.main.item_country_covid.view.deathsNumber
import kotlinx.android.synthetic.main.item_country_covid.view.flag
import kotlinx.android.synthetic.main.item_country_covid.view.recovered
import kotlinx.android.synthetic.main.item_country_covid.view.recoveredNumber

class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun renderView(countryItem: CountryItem, onCountryItemListener: (countryItem: CountryItem) -> Unit) {
        renderCard(countryItem = countryItem)
        setListener(countryItem = countryItem, onCountryItemListener = onCountryItemListener)
    }

    private fun renderCard(countryItem: CountryItem) {
        itemView.run {
            flag.setUrlCircle("https://flagpedia.net/data/flags/normal/${countryItem.countryIso1.toLowerCase()}.png")
            confirmedNumber.text = countryItem.confirmed.toDouble().formatValue()
            deathsNumber.text = countryItem.death.toDouble().formatValue()
            recoveredNumber.text = countryItem.recovered.toDouble().formatValue()
            activeNumber.text = countryItem.active.toDouble().formatValue()
            /*countryName.text = countryItem.countryName
            countryTotalNumber.text = countryItem.confirmed.toString()


            }*/
        }
    }

    private fun setListener(countryItem: CountryItem, onCountryItemListener: (countryItem: CountryItem) -> Unit) {
        itemView.setOnClickListener { onCountryItemListener.invoke(countryItem) }
    }
}
