package com.david.hackro.stats.domain

import com.david.hackro.stats.data.datasource.remote.model.DataItem

data class Regions(val data: List<DataItem>)

data class DataItem(val iso: String, val name: String)
