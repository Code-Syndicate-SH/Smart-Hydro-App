package org.smartroots.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
/**
 * @author Shravan Ramjathan
 */
@Serializable
data class Sensor(
    @SerialName("EC")
    val eC: String = "Unavailable Data",
    @SerialName("Humidity")
    val humidity: String = "Unavailable Data",
    @SerialName("Light")
    val light: String = "Unavailable Data",
    @SerialName("PH")
    val pH: String = "Unavailable Data",
    @SerialName("Temperature")
    val temperature: String = "Unavailable Data",
    @SerialName("FlowRate")
    val flowRate: String ="Unavailable Data"
)
