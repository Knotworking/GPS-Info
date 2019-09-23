package com.knotworking.gpsinfo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.knotworking.gpsinfo.location.LocationRepository
import javax.inject.Inject

/**
 * ViewModel for [MainFragment]
 */
class MainViewModel @Inject constructor(repository: LocationRepository) : ViewModel() {
    val location: String = repository.getLastKnownLocation()
//    val lastKnownTime: LiveData<Long> = TODO()
    val lastKnownTime: String = ""
}
