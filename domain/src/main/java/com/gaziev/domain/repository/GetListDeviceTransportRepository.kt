package com.gaziev.domain.repository

import com.gaziev.domain.model.DeviceTransportDetails
import io.reactivex.Single

interface GetListDeviceTransportRepository {
    operator fun invoke(): Single<List<DeviceTransportDetails>>
}