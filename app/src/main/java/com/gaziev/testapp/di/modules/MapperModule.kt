package com.gaziev.testapp.di.modules

import com.gaziev.data.mapper.DeviceTransportMapperImpl
import com.gaziev.data.mapper.Mapper
import com.gaziev.data.model.DeviceTransportEntity
import com.gaziev.domain.model.DeviceTransportDetails
import dagger.Module
import dagger.Provides

@Module
class MapperModule {

    @Provides
    fun provideDeviceTransportMapper(): Mapper<DeviceTransportEntity, DeviceTransportDetails> {
        return DeviceTransportMapperImpl()
    }
}