package org.smartroots.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sensor(
    @SerialName("EC")
    val eC: String = "",
    @SerialName("Humidity")
    val humidity: String = "",
    @SerialName("Light")
    val light: String = "",
    @SerialName("PH")
    val pH: String = "",
    @SerialName("Temperature")
    val temperature: String = "",
    @SerialName("FlowRate")
    val flowRate: String =""
)
