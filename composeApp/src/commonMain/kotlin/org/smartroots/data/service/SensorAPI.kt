package org.smartroots.data.service

import io.ktor.client.statement.HttpResponse
import org.smartroots.data.model.Sensor
/**
 * @author Shravan Ramjathan
 */
interface SensorAPI {
    suspend fun getSensorReading(baseURL:String): Sensor
    suspend fun getHistoricSensorReading(baseURL:String): List<Sensor>

    suspend fun toggleComponent(baseURL:String,component: SensorComponent): HttpResponse



}