package org.smartroots.data.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.URLBuilder
import io.ktor.http.Url
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.smartroots.data.model.Sensor
import org.smartroots.data.module.NetworkConfig

class SensorAPIImpl(
    val tentClient: HttpClient,
   val  baseURL: String
) : SensorAPI {



    override suspend fun getSensorReading(): Sensor {
        return tentClient.get("$baseURL/r/n/r/n").body()
    }

    override suspend fun getHistoricSensorReading(): List<Sensor> {
        return tentClient.get("$baseURL/historicData").body()
    }

    override suspend fun toggleLight(): HttpResponse {
        return tentClient.get("$baseURL/light").body()
    }

    override suspend fun toggleFan(): HttpResponse {
        return tentClient.get("$baseURL/fan").body()
    }

    override suspend fun toggleExtractor(): HttpResponse {
        return tentClient.get("$baseURL/extractor").body()
    }

    override suspend fun togglePump(): HttpResponse {
        return tentClient.get("$baseURL/pump").body()
    }

    override suspend fun ec(): HttpResponse {
        return tentClient.get("$baseURL/ec").body()
    }

    override suspend fun ecUp(): HttpResponse {
        return tentClient.get("$baseURL/ecUp").body()
    }

    override suspend fun ecDown(): HttpResponse {
        return tentClient.get("$baseURL/ecDown").body()
    }

    override suspend fun pH(): HttpResponse {
        return tentClient.get("$baseURL/ph").body()
    }

    override suspend fun pHUp(): HttpResponse {
        return tentClient.get("$baseURL/pHUp").body()
    }

    override suspend fun pHDown(): HttpResponse {
        return tentClient.get("$baseURL/pHDown").body()
    }


}

val SensorReadingModule = module {
    single<SensorAPI> { params ->
        SensorAPIImpl(
            baseURL = params.get(),
            tentClient = get(),

        )
    }
}
