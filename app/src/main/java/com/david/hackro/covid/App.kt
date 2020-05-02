package com.david.hackro.covid

import android.app.Application
import com.david.hackro.covid.BuildConfig.DEBUG
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
        initTimber()
    }

    private fun initKoin() {
        startKoin {
            if (DEBUG) androidLogger()

            androidContext(this@App)
        }
    }

    private fun initTimber() {
        if (DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}
