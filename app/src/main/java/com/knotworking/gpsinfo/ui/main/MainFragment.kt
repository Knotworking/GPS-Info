package com.knotworking.gpsinfo.ui.main

import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.knotworking.gpsinfo.R
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
//        val binding = MainFragmentBinding.inflate(inflater, container, false)
        val binding: MainFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        //TODO check
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

//        setupUi(binding)
        return binding.root
    }

//    private fun setupUi(binding: MainFragmentBinding) {
//        //TODO databind directly in layout
//
//        val locationObserver = Observer<Location> { location ->
//            val tempUiString = "lat: ${location.longitude}\n" +
//                    "long: ${location.latitude}\n" +
//                    "alt: ${location.altitude}\n"
//
//            binding.gpsCoordinates.text = tempUiString
//        }
//
//        viewModel.location.observe(this, locationObserver)
//    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }
}
