package com.david.hackro.covid.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.david.hackro.covid.R
import com.david.hackro.stats.domain.model.Report

class CountryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var countryList = listOf<Report>()
    lateinit var onCountryItemListener: ((report: Report) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country_covid, parent, false)

        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val countryViewHolder = holder as CountryViewHolder

        countryViewHolder.renderView(countryList[position], onCountryItemListener = onCountryItemListener)
    }

    override fun getItemCount() = countryList.size

    fun setCountryList(totalItemList: List<Report>) {
        countryList = totalItemList.sortedBy { it.country }
        notifyDataSetChanged()
    }
}
