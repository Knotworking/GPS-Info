package com.knotworking.gpsinfo.di

import com.knotworking.gpsinfo.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindFragmentsModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

}