package org.smartroots.domain

import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parameterSetOf
import org.smartroots.data.model.NetworkUrl
import org.smartroots.data.model.Sensor
import org.smartroots.data.repository.SensorRepository

// PUT MORE ERROR HANDLING FOR THIS
class GetSensorReadingsUseCase(private val getNetworkConnectionUseCase: GetNetworkConnectionUseCase) :
    KoinComponent {
    suspend operator fun invoke(): Map<String, Double> {
        var networkUrl: NetworkUrl?
        try {
            networkUrl = getNetworkConnectionUseCase()
        } catch (e: NullPointerException) {
            throw e
        }
        val sensorRepository: SensorRepository =
            getKoin().get<SensorRepository> { parameterSetOf(networkUrl?.url) }
        var sensorReadings: Sensor = sensorRepository.fetchSensorReading()
        val mappedReadings = validateSensorReading(sensorReadings)
        return mappedReadings

    }

    private fun validateSensorReading(sensor: Sensor): Map<String, Double> {

        val mappedSensorReadings = sensor.toMap()
        val validSensorReadings: MutableMap<String, Double> = mutableMapOf()
        var nullReadings = 0
        for ((key: String, value) in mappedSensorReadings.entries) {
            if (value.toDoubleOrNull() != null) {
                validSensorReadings.put(key, value.toDouble())
            } else {
                validSensorReadings.put(key, -999.9)
            }
        }
        return if (nullReadings < mappedSensorReadings.count()) validSensorReadings else emptyMap()


    }

    private fun Sensor.toMap(): Map<String, String> {  // if more get added we just expand this
        return mutableMapOf(
            "eC" to this.eC,
            "flowRate" to this.flowRate,
            "humidity" to this.humidity,
            "light" to this.light,
            "pH" to this.pH,
            "temperature" to this.temperature
        )
    }
}

