package com.gaziev.data.model

class DeviceTransportEntity(
    val titleParent: String?,
    val parent: String?,
    val type: String?,
    val title: String?,
    val lat: Double?,
    val lon: Double?
) {

    override fun toString(): String {
        return """
            titleParent = $titleParent
            parent = $parent
            type = $type
            title= $title 
            lat = $lat 
            lon = $lon
        """.trimIndent()
    }
}

