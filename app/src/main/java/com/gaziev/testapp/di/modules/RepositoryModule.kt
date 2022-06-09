package com.gaziev.testapp.di.modules

import com.gaziev.data.mapper.DeviceTransportMapperImpl
import com.gaziev.data.repository.GetListDeviceTransportRepositoryImpl
import com.gaziev.data.source.local.DeviceTransportSourceImpl
import com.gaziev.domain.repository.GetListDeviceTransportRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideGetListDeviceTransportRepository(
        deviceTransportSource: DeviceTransportSourceImpl,
        mapper: DeviceTransportMapperImpl
    ): GetListDeviceTransportRepository {
        return GetListDeviceTransportRepositoryImpl(deviceTransportSource, mapper)
    }

}