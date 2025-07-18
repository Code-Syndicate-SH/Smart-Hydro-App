package org.smartroots.data.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import org.koin.dsl.module
import org.smartroots.data.model.Sensor

/**
 * @author Shravan Ramjathan
 */
class SensorAPIImpl(
    val tentClient: HttpClient,
    val baseURL: String
) : SensorAPI {


    override suspend fun getSensorReading(): Sensor {
        return tentClient.get("$baseURL/r/n/r/n").body()
    }

    override suspend fun getHistoricSensorReading(): List<Sensor> {
        return tentClient.get("$baseURL/historicData").body()
    }

    override suspend fun toggleComponent(sensorComponent: SensorComponent): HttpResponse {
        return tentClient.get("$baseURL/${sensorComponent.componentEndpoint}").body()
    }

}

val SensorReadingModule = module {
    factory<SensorAPI> { (baseURL:String) ->
        SensorAPIImpl(
            baseURL = baseURL,
            tentClient = get(),

        )
    }
}
