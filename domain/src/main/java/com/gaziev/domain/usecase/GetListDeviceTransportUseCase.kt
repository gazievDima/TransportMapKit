package com.gaziev.domain.usecase

import com.gaziev.domain.model.DeviceTransportDetails
import com.gaziev.domain.repository.GetListDeviceTransportRepository
import io.reactivex.Single
import javax.inject.Inject

class GetListDeviceTransportUseCase @Inject constructor(
    private val getListDeviceTransportRepository: GetListDeviceTransportRepository
) {

    operator fun invoke(): Single<List<DeviceTransportDetails>> =
        getListDeviceTransportRepository()

}