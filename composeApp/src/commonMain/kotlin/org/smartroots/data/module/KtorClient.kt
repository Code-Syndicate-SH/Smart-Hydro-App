package org.smartroots.data.module



import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.smartroots.data.model.Sensor


private const val BASE_URL = "http://192.168.8.14/"
fun createHttpClient(baseURL: String): HttpClient{
   return HttpClient(){
        install(ContentNegotiation){
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }

    }
}













