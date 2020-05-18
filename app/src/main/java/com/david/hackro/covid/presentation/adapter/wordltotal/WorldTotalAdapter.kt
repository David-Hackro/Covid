package com.david.hackro.covid.presentation.adapter.wordltotal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.david.hackro.covid.R
import com.david.hackro.covid.presentation.model.WorldTotalItem

class WorldTotalAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var totalList = listOf<WorldTotalItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_world_total, parent,
            ATTACH_ROOT
        )

        return WorldTotalViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val totalViewHolder = holder as WorldTotalViewHolder

        totalViewHolder.renderView(totalList[position])
    }

    override fun getItemCount() = totalList.size

    fun setTotalList(worldTotalItemList: List<WorldTotalItem>) {
        totalList = worldTotalItemList
        notifyDataSetChanged()
    }

    private companion object {
        const val ATTACH_ROOT = false
    }
}
