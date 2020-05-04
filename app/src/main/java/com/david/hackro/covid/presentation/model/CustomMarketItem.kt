package com.david.hackro.covid.presentation.model

import com.david.hackro.stats.domain.model.Report
import com.google.android.gms.maps.model.LatLng

import com.google.maps.android.clustering.ClusterItem

class MyItem(val report: Report) : ClusterItem {

    override fun getPosition() = LatLng(report.latitude!!, report.longitude!!)

    override fun getTitle() = ""

    override fun getSnippet() = ""
}
