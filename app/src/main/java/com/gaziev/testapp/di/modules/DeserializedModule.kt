package com.gaziev.testapp.di.modules

import com.gaziev.data.model.DeviceTransportEntity
import com.gaziev.data.source.Deserialized
import com.gaziev.data.util.DeviceTransportDeserializedImpl
import dagger.Module
import dagger.Provides

@Module
class DeserializedModule {

    @Provides
    fun provideDeviceTransportDeserialized(): Deserialized<String, List<DeviceTransportEntity>> {
        return DeviceTransportDeserializedImpl()
    }
}