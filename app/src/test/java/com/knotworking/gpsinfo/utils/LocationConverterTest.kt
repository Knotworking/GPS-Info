package com.knotworking.gpsinfo.utils

import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocationConverterTest {

    //Input. Degrees: 37.7783983, isLongitude: false
    //37:46:42.23388
    //Output: 37°46'42.23" N

    //Input. Degrees: -122.391, isLongitude: true
    //-122:23:27.6
    //Output: -122°23'27.6" W

    //TODO Uses a static android method "Location.convert", so must be done in an integration test
    @Test
    @Ignore
    fun getDegreesAsDMS_normalInput_longitude() {
        // Given
        val degrees = 37.7783983
        val isLongitude = false

        // When
        val result = LocationConverter.getDegreesAsDMS(degrees, isLongitude)

        // Then
        assertEquals("37°46'42.23\" N", result)
    }

    //TODO check input validity?

}