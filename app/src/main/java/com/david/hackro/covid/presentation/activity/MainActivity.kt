package com.david.hackro.covid.presentation.activity

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.navigation.findNavController
import androidx.navigation.plusAssign
import androidx.navigation.ui.setupWithNavController
import com.david.hackro.covid.R
import com.david.hackro.covid.di.injectFeatures
import com.david.hackro.covid.presentation.navigation.KeepStateNavigator
import kotlinx.android.synthetic.main.activity_main.bottomNavView
import kotlinx.android.synthetic.main.activity_main.progress

class MainActivity : BaseActivity() {

    override fun layoutResId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectFeatures()
        setupNavigationKeepState()
    }

    private fun setupNavigationKeepState() {
        val navController = findNavController(R.id.nav_host_fragment)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)

        navHostFragment?.let {
            val navigator = KeepStateNavigator(this, it.childFragmentManager, R.id.nav_host_fragment)
            navController.navigatorProvider += navigator
        }

        navController.setGraph(R.navigation.nav_graph)

        bottomNavView.setupWithNavController(navController)
    }

    override fun showProgress() {
        progress.visibility = VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = GONE
    }
}
