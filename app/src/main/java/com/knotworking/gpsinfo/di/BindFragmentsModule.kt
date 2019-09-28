package com.knotworking.gpsinfo.di

import com.knotworking.gpsinfo.location.ui.LocationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindFragmentsModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): LocationFragment

}