package com.android.aman.exoplayer.di.components

import com.android.aman.exoplayer.api.repo.ChannelRepository
import com.android.aman.exoplayer.di.modules.ApplicationModule
import com.android.aman.exoplayer.di.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ApplicationModule::class])
interface AppComponent {
    fun inject(channelRepository: ChannelRepository)
}