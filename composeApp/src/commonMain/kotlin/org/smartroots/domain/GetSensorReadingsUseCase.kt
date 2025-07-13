package org.smartroots.domain

import org.koin.core.component.KoinComponent
import org.koin.core.module.dsl.factoryOf
import org.koin.core.parameter.parameterSetOf
import org.koin.dsl.module
import org.smartroots.data.model.Sensor
import org.smartroots.data.repository.SensorRepository

class GetSensorReadingsUseCase(private val getNetworkConnectionUseCase: GetNetworkConnectionUseCase) :
    KoinComponent {
    suspend operator fun invoke(): Sensor {
        val baseURL: String = getNetworkConnectionUseCase()
        val sensorRepository: SensorRepository =
            getKoin().get<SensorRepository> { parameterSetOf(baseURL) }
       var sensorReadings: Sensor =  sensorRepository.fetchSensorReading()
        val mappedReadings= validateSensorReading(sensorReadings)
        return mappedReadings

    }

    private fun validateSensorReading(sensor: Sensor): Sensor {
        val mappedSensorReadings = sensor.toMap()
        var nullReadings = 0
        var validReadings = true
        for ((key, value: String) in mappedSensorReadings) {
          if(value.isEmpty() || value.isBlank()){
          validReadings= false
           nullReadings++
          }
        }
        return if(nullReadings<mappedSensorReadings.count()) sensor else Sensor()


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

val GetSensorReadingsUseCaseModule = module {
    factoryOf(::GetSensorReadingsUseCase)
}