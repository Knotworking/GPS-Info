package com.knotworking.gpsinfo.location.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.knotworking.gpsinfo.R
import com.knotworking.gpsinfo.databinding.LocationFragmentBinding
import com.knotworking.gpsinfo.di.injectViewModel
import javax.inject.Inject

class LocationFragment : Fragment() {

    companion object {
        private const val PERMISSION_REQUEST_CODE = 100

        fun newInstance() = LocationFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: LocationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = injectViewModel(viewModelFactory)
        val binding: LocationFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.location_fragment, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        checkLocationPermission(requireContext())

        return binding.root
    }

    private fun checkLocationPermission(context: Context) {
        val isPermissionGranted = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED


        if (isPermissionGranted) {
            viewModel.onPermissionGranted()
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Log.i("TAG", "permissionsResult: " + requestCode + ", " + permissions[0] + ", " + grantResults[0])

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (permissions.size == 1 &&
                permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                //TODO do something
                viewModel.onPermissionGranted()
            } else {
                // Permission was denied. Display an error message.
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }
}
