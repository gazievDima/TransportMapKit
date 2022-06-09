package com.gaziev.data.repository

import com.gaziev.data.mapper.Mapper
import com.gaziev.data.model.DeviceTransportEntity
import com.gaziev.domain.model.CategoryTransport
import com.gaziev.domain.model.DeviceTransportDetails
import com.gaziev.domain.repository.GetListDeviceTransportRepository
import io.reactivex.Single
import javax.inject.Inject

class GetListDeviceTransportRepositoryImpl @Inject constructor(
    private val deviceTransportSource: DeviceTransportSource,
    private val mapper: Mapper<DeviceTransportEntity, DeviceTransportDetails>
) : GetListDeviceTransportRepository {

    override fun invoke(): Single<List<DeviceTransportDetails>> {
        return deviceTransportSource.getDeviceTransport()
            .flatMap { listEntity ->
                Single.create<List<DeviceTransportDetails>> {
                    it.onSuccess(listEntity.map { o -> mapper.mapTo(o) })
                }
            }
    }
}