package com.gaziev.testapp.di.modules

import com.gaziev.data.repository.GetListDeviceTransportRepositoryImpl
import com.gaziev.domain.usecase.GetListDeviceTransportUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetListDeviceTransport(
        getListDeviceTransportRepository: GetListDeviceTransportRepositoryImpl
    ): GetListDeviceTransportUseCase {
        return GetListDeviceTransportUseCase(getListDeviceTransportRepository)
    }

}