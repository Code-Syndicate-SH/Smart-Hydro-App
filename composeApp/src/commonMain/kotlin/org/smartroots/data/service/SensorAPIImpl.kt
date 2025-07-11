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

/**
 * @author Shravan Ramjathan
 */
class SensorAPIImpl(
    val tentClient: HttpClient,
) : SensorAPI {


    override suspend fun getSensorReading(baseURL: String): Sensor {
        return tentClient.get("$baseURL/r/n/r/n").body()
    }

    override suspend fun getHistoricSensorReading(baseURL: String): List<Sensor> {
        return tentClient.get("$baseURL/historicData").body()
    }

    override suspend fun toggleComponent(baseURL: String,sensorComponent: SensorComponent): HttpResponse {
        return tentClient.get("$baseURL/${sensorComponent.name}").body()
    }



}

val SensorReadingModule = module {
    single<SensorAPI> { params ->
        SensorAPIImpl(
            tentClient = get(),

        )
    }
}
