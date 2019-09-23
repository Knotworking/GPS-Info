package com.knotworking.gpsinfo.di

import com.knotworking.gpsinfo.GpsApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        BindActivitiesModule::class,
        ApplicationModule::class]
)
interface ApplicationComponent {

    fun inject(application: GpsApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindApplication(application: GpsApplication): Builder

        fun build(): ApplicationComponent
    }

}