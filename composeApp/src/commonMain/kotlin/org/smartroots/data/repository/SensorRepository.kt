package org.smartroots.data.repository

import io.ktor.client.statement.HttpResponse
import org.smartroots.data.model.Sensor
import org.smartroots.data.service.SensorComponent

/**
 * @author Shravan Ramjathan
 */
interface SensorRepository {
    suspend fun fetchSensorReading(): Sensor
    suspend fun fetchHistoricSensorReadings(): List<Sensor>

    //light
    suspend fun toggleComponent(sensorComponent: SensorComponent): HttpResponse

    //fan


}