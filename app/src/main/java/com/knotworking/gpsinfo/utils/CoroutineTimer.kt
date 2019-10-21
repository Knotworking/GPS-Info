package com.knotworking.gpsinfo.utils

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutineTimer {

    private val _currentTime = MutableLiveData<Long>()

    val currentTime: LiveData<Long>
        get() = _currentTime

    fun start() {
        start(1000) {
            _currentTime.postValue(SystemClock.elapsedRealtimeNanos())
        }
    }

    private fun start(repeatMillis: Long = 0, action: () -> Unit) = GlobalScope.launch {
        if (repeatMillis > 0) {
            while (true) {
                action()
                delay(repeatMillis)
            }
        } else {
            action()
        }
    }

    fun stop() {
        //TODO stop coroutine
    }

}