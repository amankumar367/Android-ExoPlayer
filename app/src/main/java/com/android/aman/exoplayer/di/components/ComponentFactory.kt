package com.android.aman.exoplayer.di.components

import com.android.aman.exoplayer.app.MainApplication
import com.android.aman.exoplayer.di.modules.ApplicationModule
import com.android.aman.exoplayer.di.modules.NetworkModule

class ComponentFactory {
    companion object{
        fun create(application: MainApplication): AppComponent {
            return DaggerAppComponent.builder()
                .applicationModule(ApplicationModule(application))
                .networkModule(NetworkModule())
                .build()
        }
    }

}