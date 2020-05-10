package com.david.hackro.covid.presentation.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.david.hackro.androidext.showError
import com.david.hackro.androidext.showNeutral
import com.david.hackro.androidext.showWarning
import com.david.hackro.androidext.snackbar
import com.david.hackro.covid.R
import com.david.hackro.domain.Failure
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId())
        hideToolbar()
    }

    private fun hideToolbar() {
        supportActionBar?.hide()
    }

    @LayoutRes protected abstract fun layoutResId(): Int

    abstract fun showProgress()

    abstract fun hideProgress()

    fun handleFailure(failure: Failure) {
        when (failure) {
            is Failure.NetworkConnection -> snackbar(window.decorView.rootView, R.string.failure_network_connection).showError()
            is Failure.CustomError -> snackbar(window.decorView.rootView, R.string.failure_unavailable).showWarning()
            is Failure.GenericError -> snackbar(window.decorView.rootView, R.string.failure_unavailable).showError()
            else -> snackbar(window.decorView.rootView, R.string.failure_unavailable).showNeutral()
        }

        Timber.d("HandleFailure: ${window.decorView.rootView} == Failure: $failure")
    }
}
