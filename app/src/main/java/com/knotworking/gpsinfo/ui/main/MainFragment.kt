package com.knotworking.gpsinfo.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.knotworking.gpsinfo.databinding.MainFragmentBinding
import com.knotworking.gpsinfo.di.injectViewModel
import javax.inject.Inject

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = injectViewModel(viewModelFactory)
        val binding = MainFragmentBinding.inflate(inflater, container, false)
        setupUi(binding)

        return binding.root
    }

    private fun setupUi(binding: MainFragmentBinding) {
        val longitudeObserver = Observer<Double> { newLongitude ->
            Log.i("TAG", "newLongitude: $newLongitude")
            binding.gpsCoordinates.text = newLongitude.toString()
        }

        viewModel.longitude.observe(this, longitudeObserver)
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
