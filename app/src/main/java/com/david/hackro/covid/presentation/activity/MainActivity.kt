package com.david.hackro.covid.presentation.activity

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import com.david.hackro.covid.R
import com.david.hackro.covid.di.injectFeatures
import com.david.hackro.covid.presentation.fragment.CountryDetailsFragment
import com.david.hackro.covid.presentation.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun layoutResId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectFeatures()
        loadFragment()
    }

    private fun loadFragment() {
        addFragment(HomeFragment.getInstance())
    }

    fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, fragment)
            .setCustomAnimations(R.animator.slide_up, 0, 0, R.animator.slide_down)
            .show(fragment)
            .commit()
    }

    private fun removeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.animator.slide_down, 0, 0, R.animator.slide_up)
            .remove(fragment)
            .commit()
    }

    override fun showProgress() {
        progress.visibility = VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = GONE
    }

    override fun onBackPressed() {
        when (supportFragmentManager.findFragmentById(R.id.fragmentContainer)) {
            is HomeFragment -> super.onBackPressed()
            is CountryDetailsFragment -> {
                supportFragmentManager.fragments.filterIsInstance<CountryDetailsFragment>().single()
                    .run {
                        removeFragment(this)
                    }
            }
        }
    }

}
