package com.gaziev.data.mapper

import android.content.ContentValues.TAG
import android.util.Log
import com.gaziev.data.model.DeviceTransportEntity
import com.gaziev.domain.model.CategoryTransport
import com.gaziev.domain.model.DeviceTransportDetails
import javax.inject.Inject

class DeviceTransportMapperImpl @Inject constructor() : Mapper<DeviceTransportEntity, DeviceTransportDetails> {

    override fun mapTo(t: DeviceTransportEntity): DeviceTransportDetails {
        val parent = when {
            t.titleParent.equals("Личные авто") -> { CategoryTransport.Private() }
            else /* Общие авто */ -> { CategoryTransport.Shared()
            }
        }

        return DeviceTransportDetails(
            parent = parent,
            type = t.type,
            title = t.title,
            lat = t.lat,
            lon = t.lon
        )
    }

    override fun mapFrom(v: DeviceTransportDetails): DeviceTransportEntity {
        return TODO()
    }
}