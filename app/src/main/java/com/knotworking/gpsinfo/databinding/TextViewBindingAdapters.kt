package com.knotworking.gpsinfo.databinding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.knotworking.gpsinfo.R
import java.util.concurrent.TimeUnit

object TextViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("elapsedRealtime", "startTime")
    fun setTimeSinceLabel(textView: TextView, elapsedRealtime: Long, startTime: Long) {
        val timeSinceSeconds =
            TimeUnit.SECONDS.convert(elapsedRealtime - startTime, TimeUnit.NANOSECONDS)
        val label = textView.context.resources.getString(R.string.elapsed_time_label)
        textView.text = String.format(label, timeSinceSeconds)
    }

}