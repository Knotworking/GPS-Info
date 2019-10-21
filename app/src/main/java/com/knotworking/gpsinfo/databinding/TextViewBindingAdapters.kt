package com.knotworking.gpsinfo.databinding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.knotworking.gpsinfo.R
import com.knotworking.gpsinfo.utils.LocationConverter
import java.util.concurrent.TimeUnit

object TextViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("elapsedRealtime", "currentTime")
    fun setTimeSinceLabel(textView: TextView, elapsedRealtime: Long, currentTime: Long) {
        val timeSinceSeconds =
            TimeUnit.SECONDS.convert(currentTime - elapsedRealtime, TimeUnit.NANOSECONDS)
        val label = textView.context.resources.getString(R.string.elapsed_time_label)
        textView.text = String.format(label, timeSinceSeconds)
    }

    @JvmStatic
    @BindingAdapter("degrees", "isLongitude")
    fun setLongitudeLatitudeText(textView: TextView, degrees: Double, isLongitude: Boolean) {
        val stringRes = if (isLongitude) R.string.longitude_label else R.string.latitude_label
        val label = textView.context.resources.getString(stringRes)
        val degreesMinutesSeconds = LocationConverter.getDegreesAsDMS(degrees, isLongitude)
        textView.text = String.format(label, degreesMinutesSeconds)
    }

}