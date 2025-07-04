package org.smartroots.data.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.smartroots.data.model.Sensor

class ApiSensorReadingImpl(val tentClient: HttpClient,val localURL:String,val remoteUrl:String): ApiSensorReading{
    override suspend fun getSensorReading(isLocal: Boolean): Sensor {
        val baseURL = if(isLocal){
            localURL
        }else{
            remoteUrl
        }
        return tentClient.get("$baseURL/r/n/r/n").body()
    }

    override suspend fun getHistoricSensorReading(isLocal: Boolean): List<Sensor> {
        val baseURL = if(isLocal){
            localURL
        }else{
            remoteUrl
        }
        return tentClient.get("$baseURL/historicData").body()
    }

}
val SensorReadingModule = module{
 single<ApiSensorReading> {params->
     ApiSensorReadingImpl(tentClient = get(), localURL = get(named("BASE_URL_LOCAL")), remoteUrl = get(named("BASE_URL_REMOTE")) )
 }
}
