package org.smartroots.data.repository

import io.ktor.client.statement.HttpResponse
import org.smartroots.data.model.Sensor
import org.smartroots.data.service.SensorComponent

/**
 * @author Shravan Ramjathan
 */
interface SensorRepository {
    suspend fun fetchSensorReading(baseURL: String): Sensor
    suspend fun fetchHistoricSensorReadings(baseURL: String): List<Sensor>

    //light
    suspend fun toggleComponent(baseURL: String,sensorComponent: SensorComponent): HttpResponse

    //fan


}