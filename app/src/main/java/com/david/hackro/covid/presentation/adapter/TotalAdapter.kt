package com.david.hackro.covid.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.david.hackro.covid.R
import com.david.hackro.covid.presentation.model.TotalItem

enum class STATUS { CONFIRMED, RECOVERED, CRITICAL, DEATHS, ACTIVE }

class TotalAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var totalList = listOf<TotalItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_total_covid, parent, false)

        return TotalViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val totalViewHolder = holder as TotalViewHolder

        totalViewHolder.renderView(totalList[position])
    }

    override fun getItemCount() = totalList.size

    fun setTotalList(totalItemList: List<TotalItem>) {
        totalList = totalItemList
        notifyDataSetChanged()
    }
}
