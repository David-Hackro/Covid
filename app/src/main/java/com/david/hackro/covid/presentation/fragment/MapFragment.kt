package com.david.hackro.covid.presentation.fragment

import android.os.Bundle
import android.view.View
import com.david.hackro.androidext.liveDataObserve
import com.david.hackro.covid.R
import com.david.hackro.covid.presentation.model.MyItem
import com.david.hackro.covid.presentation.viewmodel.MapViewModel
import com.david.hackro.domain.State
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
        initMap()
        initValues()
    }

    private fun initObservers() {
        liveDataObserve(mapViewHolder.stateDailyReport, ::onDailyReportStateChange)
    }

    private fun initMap() {
        with(mapView) {
            onCreate(null)
            getMapAsync {
                MapsInitializer.initialize(context)
                setMapLocation(it)
            }
        }
    }

    private fun initValues() {
        mapViewHolder.init()
    }

    private fun onDailyReportStateChange(state: State?) {
        state?.let { noNullState ->
            when (noNullState) {
                is State.Loading -> getActivityContext().showProgress()
                is State.Success -> {
                    val result = noNullState.responseTo<List<Report>>()

                    getActivityContext().hideProgress()

                    showDailyReports(resultList = result)
                }
                is State.Failed -> {
                    getActivityContext().run {
                        hideProgress()
                        handleFailure(failure = noNullState.failure)
                    }
                }
                else -> Timber.d("any state in onTotalReportStateChange")
            }
        }
    }

    private fun showDailyReports(resultList: List<Report>) {
        resultList.map {
            it.latitude?.run {
                addItems(it)
            }
        }
    }

    private fun setMapLocation(map: GoogleMap) {
        with(map) {
            setUpCluster(this)
            setMarketListener(this)
        }
    }

    private fun setMarketListener(googleMap: GoogleMap) {
        googleMap.setInfoWindowAdapter(mClusterManager.markerManager)

        mClusterManager.setOnClusterItemClickListener {
            it?.let {
                mClusterManager.markerCollection.setInfoWindowAdapter(CustomMarkerInfoWindowView(context = context, myItem = it))
            }

            SET_ON_CLUSTER_ITEM_CLICK_LISTENER
        }
    }

    private fun setUpCluster(googleMap: GoogleMap) {
        mClusterManager = ClusterManager(context, googleMap)
        googleMap.setOnCameraIdleListener(mClusterManager)
        googleMap.setOnMarkerClickListener(mClusterManager)
    }

    private fun addItems(report: Report) {
        mClusterManager.addItem(MyItem(report))
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

    private companion object {
        const val SET_ON_CLUSTER_ITEM_CLICK_LISTENER = false
    }
}
