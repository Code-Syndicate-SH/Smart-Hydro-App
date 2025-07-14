package org.smartroots.data.repository

import io.ktor.client.statement.HttpResponse
import io.ktor.http.parameters
import org.koin.core.parameter.parameterSetOf
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import org.smartroots.data.model.Sensor
import org.smartroots.data.service.SensorAPI
import org.smartroots.data.service.SensorComponent

/**
 *  @author Shravan Ramjatha
 */
class SensorRepositoryImpl( val sensorApi: SensorAPI): SensorRepository {

    override suspend fun fetchSensorReading(): Sensor {
        return sensorApi.getSensorReading()
    }

    override suspend fun fetchHistoricSensorReadings(): List<Sensor> {
        return sensorApi.getHistoricSensorReading()
    }

    override suspend fun toggleComponent(sensorComponent: SensorComponent): HttpResponse {
        return sensorApi.toggleComponent(sensorComponent)
    }

}
val sensorRepositoryModule = module{
   factory<SensorRepository> {(baseURL:String)->
       val api = get<SensorAPI>{ parametersOf(baseURL) }
       SensorRepositoryImpl(api)
   }
}