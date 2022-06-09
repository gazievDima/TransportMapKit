package com.gaziev.data.util

import com.gaziev.data.model.DeviceTransportEntity
import com.gaziev.data.source.Deserialized
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

class DeviceTransportDeserializedImpl @Inject constructor() :
    Deserialized<String, List<DeviceTransportEntity>> {

    override fun get(data: String): List<DeviceTransportEntity> {
        val jsonObject = JSONObject(data)
        val jsonObjectDevices = jsonObject.getJSONObject("devices")
        val jsonObjectList: JSONArray = jsonObjectDevices.getJSONArray("list")

        val titleMap: MutableMap<String, String> = mutableMapOf()
        for(i in 0 until jsonObjectList.length()) {
            val o = jsonObjectList[i] as JSONObject
            if(o["parent"].equals("root")) titleMap[o["group"] as String] = o["title"] as String
        }

        val list: MutableList<DeviceTransportEntity> = mutableListOf()
        for(i in 0 until jsonObjectList.length()) {
            val o = jsonObjectList[i] as JSONObject
            if(!o["parent"].equals("root")) list.add(
                DeviceTransportEntity(
                    titleParent = titleMap[o["parent"] as String],
                    parent = o["parent"] as String,
                    type = o["type"] as String,
                    title = o["title"] as String,
                    lat = o["lat"] as Double,
                    lon = o["lon"] as Double
                )
            )
        }
        return list
    }

}