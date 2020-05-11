package com.david.hackro.covid.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.david.hackro.covid.R

class CountryContainerFragment : BaseFragment() {
    override fun layoutId() = R.layout.fragment_country_container

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
