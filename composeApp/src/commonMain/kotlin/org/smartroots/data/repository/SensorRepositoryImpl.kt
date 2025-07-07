package org.smartroots.data.repository

import io.ktor.client.statement.HttpResponse
import io.ktor.http.parameters
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.smartroots.data.model.Sensor
import org.smartroots.data.service.SensorAPI

/**
 *  @author Shravan Ramjatha
 */
class SensorRepositoryImpl(val baseURL: String, val sensorAPI: SensorAPI): SensorRepository {

    override suspend fun fetchSensorReading(): Sensor {
        return sensorAPI.getSensorReading()
    }

    override suspend fun fetchHistoricSensorReadings(): List<Sensor> {
        return sensorAPI.getHistoricSensorReading()
    }

    override suspend fun toggleLight(): HttpResponse {
        return sensorAPI.toggleLight()
    }

    override suspend fun toggleFan(): HttpResponse {
        return sensorAPI.toggleFan()
    }

    override suspend fun toggleExtractor(): HttpResponse {
        return sensorAPI.toggleExtractor()
    }

    override suspend fun togglePump(): HttpResponse {
        return sensorAPI.togglePump()
    }

    override suspend fun ec(): HttpResponse {
        return sensorAPI.ec()
    }

    override suspend fun ecUp(): HttpResponse {
        return sensorAPI.ecUp()
    }

    override suspend fun ecDown(): HttpResponse {
        return sensorAPI.ecDown()
    }

    override suspend fun pH(): HttpResponse {
        return sensorAPI.pH()
    }

    override suspend fun pHUp(): HttpResponse {
        return sensorAPI.pHUp()
    }

    override suspend fun pHDown(): HttpResponse {
        return sensorAPI.pHDown()
    }
}
val sensorRepositoryModule = module{
   factory<SensorRepository> {params-> SensorRepositoryImpl(baseURL =params.get(), get()) }
}