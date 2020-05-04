package com.david.hackro.covid.presentation.fragment

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import com.david.hackro.covid.R
import com.david.hackro.covid.presentation.model.MyItem
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import kotlinx.android.synthetic.main.market_info_window_map.view.colorBarActive
import kotlinx.android.synthetic.main.market_info_window_map.view.colorBarDeath
import kotlinx.android.synthetic.main.market_info_window_map.view.colorBarRecovered
import kotlinx.android.synthetic.main.market_info_window_map.view.colorBarTotal
import kotlinx.android.synthetic.main.market_info_window_map.view.countryActiveNumber
import kotlinx.android.synthetic.main.market_info_window_map.view.countryDeathNumber
import kotlinx.android.synthetic.main.market_info_window_map.view.countryName
import kotlinx.android.synthetic.main.market_info_window_map.view.countryRecoveredNumber
import kotlinx.android.synthetic.main.market_info_window_map.view.countryTotalNumber


class CustomMarkerInfoWindowView(private val context: Context?, private val myItem: MyItem) : GoogleMap.InfoWindowAdapter {

    override fun getInfoContents(market: Marker?): View {
        val view = View.inflate(context, R.layout.market_info_window_map, null)

        view.run {

            countryName.text = myItem.report.country

            myItem.report.total.run {
                countryTotalNumber.text = confirmed.toString()
                countryActiveNumber.text = active.toString()
                countryRecoveredNumber.text = recovered.toString()
                countryDeathNumber.text = deaths.toString()

                customProgress(
                    progressBar = colorBarTotal,
                    progressMax = confirmed,
                    progressStatus = confirmed,
                    color = R.color.confirmed
                )
                customProgress(
                    progressBar = colorBarActive,
                    progressMax = confirmed,
                    progressStatus = active,
                    color = R.color.active
                )
                customProgress(
                    progressBar = colorBarRecovered,
                    progressMax = confirmed,
                    progressStatus = recovered,
                    color = R.color.recovered
                )
                customProgress(
                    progressBar = colorBarDeath,
                    progressMax = confirmed,
                    progressStatus = deaths,
                    color = R.color.deaths
                )
            }
        }

        return view
    }

    private fun customProgress(progressBar: ProgressBar, progressMax: Int, progressStatus: Int, color: Int) {
        progressBar.run {
            max = progressMax
            progress = progressStatus
            progressBackgroundTintList = ContextCompat.getColorStateList(context, color)
            progressTintList = ContextCompat.getColorStateList(context, color)
        }
    }

    override fun getInfoWindow(market: Marker?) = null
}
