package com.david.hackro.covid.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.david.hackro.covid.R
import com.david.hackro.covid.presentation.model.CountryTotalItem

class CountryTotalAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var countryTotalItemList = listOf<CountryTotalItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country_total, parent, ATTACH_ROOT)

        return CountryTotalViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val countryTotalViewHolder = holder as CountryTotalViewHolder

        countryTotalViewHolder.renderView(countryTotalItemList[position])
    }

    override fun getItemCount() = countryTotalItemList.size

    fun setCountryItemList(countryTotalItemList: List<CountryTotalItem>) {
        this.countryTotalItemList = countryTotalItemList
        notifyDataSetChanged()
    }

    private companion object {
        const val ATTACH_ROOT = false
    }
}
