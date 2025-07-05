package org.smartroots.data.repository

import io.ktor.client.statement.HttpResponse
import io.ktor.http.parameters
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.smartroots.data.model.Sensor
import org.smartroots.data.service.SensorAPI

class SensorRepositoryImpl(val usingLocalNetwork: Boolean, val sensorAPI: SensorAPI): SensorRepository {
    override suspend fun fetchSensorReading(): Sensor {
        TODO("Not yet implemented")
    }

    override suspend fun fetchHistoricSensorReadings(): List<Sensor> {
        TODO("Not yet implemented")
    }

    override suspend fun toggleLight(): HttpResponse {
        TODO("Not yet implemented")
    }

    override suspend fun toggleFan(): HttpResponse {
        TODO("Not yet implemented")
    }

    override suspend fun toggleExtractor(): HttpResponse {
        TODO("Not yet implemented")
    }

    override suspend fun togglePump(): HttpResponse {
        TODO("Not yet implemented")
    }

    override suspend fun ec(): HttpResponse {
        TODO("Not yet implemented")
    }

    override suspend fun ecUp(): HttpResponse {
        TODO("Not yet implemented")
    }

    override suspend fun ecDown(): HttpResponse {
        TODO("Not yet implemented")
    }

    override suspend fun pH(): HttpResponse {
        TODO("Not yet implemented")
    }

    override suspend fun pHUp(): HttpResponse {
        TODO("Not yet implemented")
    }

    override suspend fun pHDown(): HttpResponse {
        TODO("Not yet implemented")
    }
}
val sensorRepositoryModule = module{
   factory<SensorRepository> {params-> SensorRepositoryImpl(usingLocalNetwork = params.get(), get()) }
}