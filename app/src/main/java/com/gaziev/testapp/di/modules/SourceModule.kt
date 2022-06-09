package com.gaziev.testapp.di.modules

import android.app.Application
import android.content.res.AssetManager
import com.gaziev.data.repository.DeviceTransportSource
import com.gaziev.data.source.local.DeviceTransportSourceImpl
import com.gaziev.data.util.DeviceTransportDeserializedImpl
import dagger.Module
import dagger.Provides

@Module
class SourceModule {

    @Provides
    fun provideDeviceTransportSource(
        assetManager: AssetManager,
        deserialized: DeviceTransportDeserializedImpl
    ): DeviceTransportSource {
        return DeviceTransportSourceImpl(assetManager, deserialized)
    }

    @Provides
    fun provideAssetsManager(application: Application): AssetManager {
        return application.assets
    }
}