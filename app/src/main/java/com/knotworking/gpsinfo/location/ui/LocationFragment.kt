package com.knotworking.gpsinfo.location.ui

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.knotworking.gpsinfo.R
import com.knotworking.gpsinfo.databinding.LocationFragmentBinding
import com.knotworking.gpsinfo.di.injectViewModel
import javax.inject.Inject

class LocationFragment : Fragment() {

    companion object {
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
        binding.location = viewModel.location
        binding.startTime = SystemClock.elapsedRealtimeNanos()
        return binding.root
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
