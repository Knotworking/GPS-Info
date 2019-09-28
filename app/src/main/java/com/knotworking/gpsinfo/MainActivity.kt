package com.knotworking.gpsinfo

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.knotworking.gpsinfo.location.ui.LocationFragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    private val LOCATION_FRAGMENT = "locationFragment"
    private val PERMISSION_REQUEST_CODE = 100

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LocationFragment.newInstance(), LOCATION_FRAGMENT)
                .commitNow()
        }

        checkLocationPermission()
    }

    private fun checkLocationPermission() {
        val isPermissionGranted = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (isPermissionGranted) {
            Log.i("TAG", "Permission Granted")
        } else {
            Log.i("TAG", "Permission Denied")
            // Show rationale and request permission.
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {

        Log.i("TAG",
            "onRequestPermissionsResult: " + requestCode + ", " + permissions[0] + ", " + grantResults[0])

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (permissions.size == 1 &&
                permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                //TODO broadcast to fragment to start tracking
            } else {
                // Permission was denied. Display an error message.
            }
        }
    }
}
