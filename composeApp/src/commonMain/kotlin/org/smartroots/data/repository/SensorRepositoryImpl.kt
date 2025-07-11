package org.smartroots.data.repository

import io.ktor.client.statement.HttpResponse
import io.ktor.http.parameters
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.smartroots.data.model.Sensor
import org.smartroots.data.service.SensorAPI
import org.smartroots.data.service.SensorAPIImpl
import org.smartroots.data.service.SensorComponent

/**
 *  @author Shravan Ramjatha
 */
class SensorRepositoryImpl( val sensorAPI: SensorAPI): SensorRepository {

    override suspend fun fetchSensorReaading(baseURL: String): Sensor {
        return sensorAPI.getSensorReading(baseURL)
    }

    override suspend fun fetchHistoricSensorReadings(baseURL: String): List<Sensor> {
        return sensorAPI.getHistoricSensorReading(baseURL)
    }

    override suspend fun toggleComponent(baseURL: String,sensorComponent: SensorComponent): HttpResponse {
        return sensorAPI.toggleComponent(baseURL,sensorComponent)
    }

}
val sensorRepositoryModule = module{
   factory<SensorRepository> { SensorRepositoryImpl(get()) }
}