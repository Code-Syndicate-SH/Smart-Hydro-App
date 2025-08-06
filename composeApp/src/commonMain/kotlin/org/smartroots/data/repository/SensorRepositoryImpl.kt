package org.smartroots.data.repository

import io.ktor.client.statement.HttpResponse
import org.smartroots.data.model.Sensor
import org.smartroots.data.service.SensorAPI
import org.smartroots.data.service.SensorComponent

/**
 *  @author Shravan Ramjatha
 */
class SensorRepositoryImpl(val sensorApi: SensorAPI) : SensorRepository {

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
