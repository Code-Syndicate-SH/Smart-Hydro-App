package org.smartroots.presentation.state

import org.smartroots.data.model.Sensor

data class HomeUI(
    val sensorReadings: Map<String,String> = emptyMap(),
    val languagePreference: String = "English",
)