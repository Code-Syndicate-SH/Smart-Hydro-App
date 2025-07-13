package org.smartroots.data.service

import io.ktor.client.statement.HttpResponse
import org.smartroots.data.model.Sensor
/**
 * @author Shravan Ramjathan
 */
interface SensorAPI {
    suspend fun getSensorReading(): Sensor
    suspend fun getHistoricSensorReading(): List<Sensor>

    suspend fun toggleComponent(component: SensorComponent): HttpResponse



}