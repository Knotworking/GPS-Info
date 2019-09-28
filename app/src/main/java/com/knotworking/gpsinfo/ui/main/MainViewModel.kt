package com.knotworking.gpsinfo.ui.main

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.knotworking.gpsinfo.location.LocationRepository
import javax.inject.Inject

/**
 * ViewModel for [MainFragment]
 */
class MainViewModel @Inject constructor(private val repository: LocationRepository) : ViewModel() {
//    val longitude = repository.longitude
    val location: LiveData<Location> = repository.location

    fun onStart() {
        repository.startTracking()
    }

    fun onStop() {
        repository.stopTracking()
    }
}
