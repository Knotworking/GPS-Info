package com.knotworking.gpsinfo.location

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import javax.inject.Inject

class LocationRepository @Inject constructor(context: Context) {

    private var fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    fun getLastKnownLocation(): String {
        return "3.576, 65.563 " + fusedLocationClient.instanceId
    }

    fun getLastKnownTime(): LiveData<Long> {
        val data = MutableLiveData<Long>()
        data.value = System.currentTimeMillis()
        return data
    }
}