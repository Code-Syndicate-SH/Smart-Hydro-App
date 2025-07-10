package org.smartroots.data.repository

import io.ktor.client.statement.HttpResponse
import org.smartroots.data.model.Sensor
/**
 * @author Shravan Ramjathan
 */
interface SensorRepository {
    suspend fun fetchSensorReading(): Sensor
    suspend fun fetchHistoricSensorReadings(): List<Sensor>

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