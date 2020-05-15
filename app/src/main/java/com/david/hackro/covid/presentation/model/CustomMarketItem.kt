package com.david.hackro.covid.presentation.model

import com.david.hackro.kotlinext.empty
import com.david.hackro.stats.domain.model.DataByStatusItem
import com.google.android.gms.maps.model.LatLng

import com.google.maps.android.clustering.ClusterItem

class MyItem(val dataByStatus: DataByStatusItem) : ClusterItem {

    override fun getPosition() = LatLng(dataByStatus.lat!!.toDouble(), dataByStatus.lng!!.toDouble())

    override fun getTitle() = String.empty()

    override fun getSnippet() = String.empty()
}
