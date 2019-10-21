package com.knotworking.gpsinfo.location.ui

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.knotworking.gpsinfo.location.data.LocationRepository
import com.knotworking.gpsinfo.utils.CoroutineTimer
import javax.inject.Inject

/**
 * ViewModel for [LocationFragment]
 */
class LocationViewModel @Inject constructor(private val repository: LocationRepository) : ViewModel() {

    private val timer = CoroutineTimer()

    val location: LiveData<Location> = repository.location
    val satellites: LiveData<Int> = repository.satellites
    val currentTime: LiveData<Long> = timer.currentTime

    fun onPermissionGranted() {
        repository.onPermissionGranted()
    }

    fun onStart() {
        repository.startTracking()
        timer.start()
    }

    fun onStop() {
        repository.stopTracking()
        // Stop timer
    }

    fun resetClick() {

    }
}
