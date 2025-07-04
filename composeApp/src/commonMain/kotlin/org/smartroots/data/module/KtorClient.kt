package org.smartroots.data.module



import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module




fun createHttpClient(): HttpClient{
   return HttpClient(){
        install(ContentNegotiation){
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }

    }
}
val ktorClientModule = module {
 single(named("BASE_URL_LOCAL")){"http://192.168.8.14/"}
 single(named("BASE_URL_REMOTE")){"http://192.168.8.14/"}
 single{createHttpClient()}
}












