package com.david.hackro.covid.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.david.hackro.androidext.liveDataObserve
import com.david.hackro.covid.R
import com.david.hackro.covid.presentation.model.MyItem
import com.david.hackro.covid.presentation.viewmodel.MapViewModel
import com.david.hackro.domain.State
import com.david.hackro.stats.domain.model.GetDataLatest
import com.david.hackro.stats.domain.model.Report
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.maps.android.clustering.ClusterManager
import kotlinx.android.synthetic.main.fragment_map.mapView
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MapFragment : BaseFragment() {

    override fun layoutId() = R.layout.fragment_map
    private lateinit var mClusterManager: ClusterManager<MyItem>

    private val mapViewHolder: MapViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initValues()

        with(mapView) {
            // Initialise the MapView
            onCreate(null)
            // Set the map ready callback to receive the GoogleMap object
            getMapAsync {
                MapsInitializer.initialize(context)
                setMapLocation(it)
            }
        }
    }

    private fun initObservers() {
        liveDataObserve(mapViewHolder.stateDataLatest, ::onDataLatestStateChange)
        liveDataObserve(mapViewHolder.stateDailyReport, ::onDailyReportStateChange)
    }

    private fun initValues() {
        mapViewHolder.init()
    }

    private fun onDataLatestStateChange(state: State?) {
        state?.let { noNullState ->
            when (noNullState) {
                is State.Success -> {

                    val result = noNullState.responseTo<List<GetDataLatest>>()

                    showDataLatest(resultList = result)
                }
                else -> Timber.d("any state in onTotalReportStateChange")
            }
        }
    }


    private fun onDailyReportStateChange(state: State?) {
        state?.let { noNullState ->
            when (noNullState) {
                is State.Success -> {

                    val result = noNullState.responseTo<List<Report>>()

                    showDailyReports(resultList = result)
                }
                else -> Timber.d("any state in onTotalReportStateChange")
            }
        }
    }

    private fun showDailyReports(resultList: List<Report>) {
        resultList.map {

            if (it.latitude != null && it.longitude != null) {
                addItems(it.latitude!!, it.longitude!!)
            }

        }
    }

    private fun showDataLatest(resultList: List<GetDataLatest>) {
        resultList.map {

            if (it.latitude != null && it.longitude != null) {

                addItems(it.latitude!!.toDouble(), it.longitude!!.toDouble())
            }

        }
    }


    private fun setMapLocation(map: GoogleMap) {
        with(map) {
            setUpClusterer(this)
            setOnMapClickListener {
                Toast.makeText(context, "Clicked on map", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setUpClusterer(googleMap: GoogleMap) { // Position the map.
        mClusterManager = ClusterManager(context, googleMap)
        googleMap.setOnCameraIdleListener(mClusterManager)
        googleMap.setOnMarkerClickListener(mClusterManager)
    }

    private fun addItems(lat: Double, lng: Double) {
        mClusterManager.addItem(MyItem(lat, lng, "", " "))
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }
}
