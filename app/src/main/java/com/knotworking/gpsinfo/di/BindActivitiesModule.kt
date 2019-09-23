package com.knotworking.gpsinfo.di

import com.knotworking.gpsinfo.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindActivitiesModule {

    @ContributesAndroidInjector(modules = [BindFragmentsModule::class])
    abstract fun contributeActivityAndroidInjector(): MainActivity

}