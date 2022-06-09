package com.gaziev.domain.model

class DeviceTransportDetails(
    val parent: CategoryTransport?,
    val type: String?,
    val title: String?,
    val lat: Double?,
    val lon: Double?
) {
    override fun toString(): String {
        return """
            parent: ${parent?.root}
            type: $type
            title: $title
            lat: $lat
            lon: $lon
        """.trimIndent()
    }
}