package com.knotworking.gpsinfo.location.ui

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.knotworking.gpsinfo.location.data.LocationRepository
import javax.inject.Inject

/**
 * ViewModel for [LocationFragment]
 */
class LocationViewModel @Inject constructor(private val repository: LocationRepository) : ViewModel() {
    val location: LiveData<Location> = repository.location

    fun onStart() {
        repository.startTracking()
    }

    fun onStop() {
        repository.stopTracking()
    }
}
