package com.example.sabbir.pixabaysearch.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by himelhimu on 11/21/2021
 * Md Sabbir Ahmed (Himel)
 * This is a free of charge whatever you want to do with it code,
 * However the individul librays used in here might haver their own licensing.
 */

fun isInternetAvailable(context: Context): Boolean {
    var isConnected: Boolean = false // Initial Value
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}