package com.david.hackro.androidext

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

val Context.networkInfo: NetworkInfo? get() = (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
