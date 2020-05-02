package com.david.hackro.covid.presentation.activity

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.david.hackro.covid.R
import com.david.hackro.covid.di.injectFeatures
import kotlinx.android.synthetic.main.activity_main.bottomNav

class MainActivity : BaseActivity() {

    override fun layoutResId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectFeatures()
        initNavController()
    }

    private fun initNavController() {
        val navController = findNavController(R.id.navHost)
        bottomNav.setupWithNavController(navController)
    }

}
