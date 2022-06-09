package com.gaziev.data.repository

import com.gaziev.data.model.DeviceTransportEntity
import io.reactivex.Single

interface DeviceTransportSource {
    fun getDeviceTransport(): Single<List<DeviceTransportEntity>>
}