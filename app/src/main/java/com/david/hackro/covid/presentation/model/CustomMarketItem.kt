package com.david.hackro.covid.presentation.model

import com.google.android.gms.maps.model.LatLng

import com.google.maps.android.clustering.ClusterItem

class MyItem : ClusterItem {
    private val mPosition: LatLng
    private lateinit var mTitle: String
    private lateinit var mSnippet: String

    constructor(lat: Double, lng: Double) {
        mPosition = LatLng(lat, lng)
    }

    constructor(lat: Double, lng: Double, title: String, snippet: String) {
        mPosition = LatLng(lat, lng)
        mTitle = title
        mSnippet = snippet
    }

    override fun getPosition(): LatLng {
        return mPosition
    }

    override fun getTitle() = ""

    override fun getSnippet() = ""
}
