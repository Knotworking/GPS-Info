package com.knotworking.gpsinfo.location

import android.content.Context
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class LocationRepository @Inject constructor(context: Context) {

    private var fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    private val builder = LocationSettingsRequest.Builder()

    private val client: SettingsClient = LocationServices.getSettingsClient(context)
    private val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())

    private lateinit var locationCallback: LocationCallback

    private var requestingLocationUpdates = false
    private var locationUpdatesStarted = false

    private val locationRequest = LocationRequest.create()?.apply {
        interval = 2000
        fastestInterval = 2000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    val longitude = MutableLiveData<Double>()

    init {
        setupLocationSettingsCheck()
        setupLocationCallback()
    }

    private fun setupLocationSettingsCheck() {
        task.addOnSuccessListener {
            Log.i("LocationRepository", "check location settings task success")
            // All location settings are satisfied. The client can initialize location requests here.

            requestingLocationUpdates = true

            startTracking()
        }

        task.addOnFailureListener { exception ->
            Log.i("LocationRepository", "check location settings task failure")

            requestingLocationUpdates = false

            if (exception is ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed by showing the user a dialog.

//                try {
//                    // Show the dialog by calling startResolutionForResult(),
//                    // and check the result in onActivityResult().
//                    exception.startResolutionForResult(this@MainActivity,
//                        REQUEST_CHECK_SETTINGS)
//                } catch (sendEx: IntentSender.SendIntentException) {
//                    // Ignore the error.
//                }
            }
        }
    }

    private fun setupLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations) {
                    // Update UI with location data

                    longitude.value = location.longitude
                }
            }
        }
    }

    fun startTracking() {
        if (requestingLocationUpdates && !locationUpdatesStarted) {
            Log.i("LocationRepository", "start tracking location")
            locationUpdatesStarted = true
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        } else {
            Log.i(
                "LocationRepository",
                "can't track location: requestingUpdates: $requestingLocationUpdates, started: $locationUpdatesStarted"
            )
        }
    }

    fun stopTracking() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
        locationUpdatesStarted = false

        Log.i("LocationRepository", "stop tracking location")
    }
}