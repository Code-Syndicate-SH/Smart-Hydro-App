package org.smartroots.data.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import io.ktor.http.URLProtocol
import io.ktor.http.path
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.smartroots.data.model.Sensor

class SensorAPIImpl(val tentClient: HttpClient, val localURL:String, val remoteUrl:String,): SensorAPI{
    constructor( tentClient: HttpClient,  localURL:String,  remoteUrl:String, isLocal:Boolean):this(tentClient, localURL,  remoteUrl){

            if(isLocal){
                baseURL=localURL
            }else{
                remoteUrl
            }
    }
    private var baseURL: String =""

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

    override suspend fun toggleLight(): HttpResponse {
        return tentClient.get("$baseURL/light").body()
    }

    override suspend fun toggleFan(): HttpResponse {
        return tentClient.get{
        url{
            protocol = URLProtocol.HTTP
            host = baseURL
            path("light")
        }
        }
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
val SensorReadingModule = module{
 single<SensorAPI> { params->
     SensorAPIImpl(tentClient = get(), localURL = get(named("BASE_URL_LOCAL")), remoteUrl = get(named("BASE_URL_REMOTE")) )
 }
}
