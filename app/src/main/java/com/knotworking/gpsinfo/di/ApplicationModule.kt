package com.knotworking.gpsinfo.di

import android.content.Context
import com.knotworking.gpsinfo.GpsApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class ApplicationModule {

    @Provides
    @Singleton
    fun provideContext(application: GpsApplication): Context {
        return application
    }

}