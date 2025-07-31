package org.smartroots.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author Shravan Ramjathan
 */
const val unavailableData = "Unavailable Data"
@Serializable
data class Sensor(
    @SerialName("EC")
    val eC: String = unavailableData,
    @SerialName("Humidity")
    val humidity: String = unavailableData,
    @SerialName("Light")
    val light: String = unavailableData,
    @SerialName("PH")
    val pH: String = unavailableData,
    @SerialName("Temperature")
    val temperature: String = unavailableData,
    @SerialName("FlowRate")
    val flowRate: String = unavailableData,
)
