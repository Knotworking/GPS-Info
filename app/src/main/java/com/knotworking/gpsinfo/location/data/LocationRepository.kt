package com.knotworking.gpsinfo.location.data

import android.annotation.SuppressLint
import android.content.Context
import android.location.GpsStatus
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import javax.inject.Inject


class LocationRepository @Inject constructor(context: Context): GpsStatus.Listener {

    //TODO only expose LiveData (not mutable)
    val location = MutableLiveData<Location>()
    val satellites = MutableLiveData<Int>()

    private var fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    private val builder = LocationSettingsRequest.Builder()

    private val client: SettingsClient = LocationServices.getSettingsClient(context)
    private val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())

    private val locationManager: LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    private lateinit var locationCallback: LocationCallback

    private var permissionGranted = false
    private var requestingLocationUpdates = false
    private var locationUpdatesStarted = false

    private val locationRequest = LocationRequest.create()?.apply {
        interval = 2000
        fastestInterval = 2000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    init {
        setupLocationSettingsCheck()
        setupLocationCallback()
    }

    fun onPermissionGranted() {
        permissionGranted = true
        setupLocationManager()
        startTracking()
    }

    @SuppressLint("MissingPermission")
    private fun setupLocationManager() {
        locationManager.addGpsStatusListener(this)
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
                for (loc in locationResult.locations) {
                    // Update UI with location data
                    location.value = loc
                }
            }
        }
    }

    // From GpsStatus.Listener
    override fun onGpsStatusChanged(event: Int) {
        getSatellites()
    }

    @SuppressLint("MissingPermission")
    private fun getSatellites() {
        var seenSatellites = 0
        var satellitesInFix = 0

        for (sat in locationManager.getGpsStatus(null).satellites) {
            if (sat.usedInFix()) {
                satellitesInFix++
            }
            seenSatellites++
        }
        Log.i("TAG", "$seenSatellites Used In Last Fix ($satellitesInFix)")
        satellites.value = seenSatellites
    }

    fun startTracking() {
        if (permissionGranted && requestingLocationUpdates && !locationUpdatesStarted) {
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
                "can't track location:\n" +
                        "permissionGranted: $permissionGranted\n" +
                        "requestingUpdates: $requestingLocationUpdates\n" +
                        "started: $locationUpdatesStarted"
            )
        }
    }

    fun stopTracking() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
        locationUpdatesStarted = false

        Log.i("LocationRepository", "stop tracking location")
    }
}