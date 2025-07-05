package org.smartroots.data.service

import io.ktor.client.statement.HttpResponse
import org.smartroots.data.model.Sensor

interface SensorAPI {
    suspend fun getSensorReading(isLocal: Boolean): Sensor
    suspend fun getHistoricSensorReading(isLocal: Boolean): List<Sensor>

    //light
    suspend fun toggleLight(): HttpResponse

    //fan
    suspend fun toggleFan(): HttpResponse

    //extract
    suspend fun toggleExtractor(): HttpResponse

    //pump
    suspend fun togglePump(): HttpResponse

    //ec
    suspend fun ec(): HttpResponse

    //ecUp
    suspend fun ecUp(): HttpResponse

    //ecDown
    suspend fun ecDown(): HttpResponse

    //ph
    suspend fun pH(): HttpResponse

    //phUp
    suspend fun pHUp(): HttpResponse

    //phDown
    suspend fun pHDown(): HttpResponse


}