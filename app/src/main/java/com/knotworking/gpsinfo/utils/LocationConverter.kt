package com.knotworking.gpsinfo.utils

import android.location.Location
import android.location.Location.FORMAT_SECONDS


object LocationConverter {

    fun getDegreesAsDMS(degrees: Double, isLongitude: Boolean): String {
        var dmsString = Location.convert(degrees, FORMAT_SECONDS)
        val compassPoint = if (isLongitude) "W" else "N"
        dmsString = replaceDelimiters(dmsString)
        dmsString = "$dmsString $compassPoint"
        return dmsString
    }

    private fun replaceDelimiters(inputString: String): String {
        var string = inputString
        val decimalPlace = 2

        string = string.replaceFirst(":".toRegex(), "°")
        string = string.replaceFirst(":".toRegex(), "'")
        val pointIndex = string.indexOf(".")
        val endIndex = pointIndex + 1 + decimalPlace
        if (endIndex < string.length) {
            string = string.substring(0, endIndex)
        }
        string += "\""
        return string
    }
}