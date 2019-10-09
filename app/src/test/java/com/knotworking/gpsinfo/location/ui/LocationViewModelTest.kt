package com.knotworking.gpsinfo.location.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.knotworking.gpsinfo.location.data.LocationRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@RunWith(JUnit4::class)
class LocationViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val NUMBER_OF_SATELLITES = 5
    private val satellites = MutableLiveData<Int>()

    private val repository = mock(LocationRepository::class.java)
    private lateinit var viewModel: LocationViewModel

    @Before
    fun init() {
        satellites.value = NUMBER_OF_SATELLITES
        `when`(repository.satellites).thenReturn(satellites)

        viewModel = LocationViewModel(repository)
    }

    @Test
    fun onPermissionGranted() {
        viewModel.onPermissionGranted()
        Mockito.verify(repository).onPermissionGranted()
    }

    @Test
    fun satelliteLiveDataFromRepository() {
        Assert.assertEquals(viewModel.satellites.value, NUMBER_OF_SATELLITES)
    }

}