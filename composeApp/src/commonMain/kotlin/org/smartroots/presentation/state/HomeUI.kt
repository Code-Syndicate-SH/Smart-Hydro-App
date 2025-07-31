package org.smartroots.presentation.state

data class HomeUI(
    val sensorReadings: Map<String,Double> = emptyMap(),
    val sensorError:String = "",
    val networkError:String = "",
    val languagePreference: String = "English",
)