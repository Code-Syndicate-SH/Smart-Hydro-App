package org.smartroots.data

import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.builtins.ListSerializer
import org.smartroots.data.model.Sensor
import org.smartroots.data.service.SensorAPIImpl
import org.smartroots.data.service.SensorComponent
import kotlin.test.*

class SensorApiEdgeTest {

    private val baseUrl = "http://localhost"

    private fun createClientWithResponse(
        response: String,
        status: HttpStatusCode = HttpStatusCode.OK,
        endpoint: String = "/r/n/r/n"
    ): HttpClient {
        return HttpClient(MockEngine) {
            engine {
                addHandler {
                    if (it.url.encodedPath == endpoint) {
                        respond(response, status, headersOf(HttpHeaders.ContentType, "application/json"))
                    } else {
                        respondError(HttpStatusCode.NotFound)
                    }
                }
            }
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }
    }

    @Test
    fun testGetSensorReading_withEmptyJson() = runTest {
        val client = createClientWithResponse("{}")
        val api = SensorAPIImpl(client, baseUrl)

        val result = api.getSensorReading()

        assertEquals("Unavailable Data", result.eC)
        assertEquals("Unavailable Data", result.humidity)
    }


    @Test
    fun testGetHistoricSensorReading_withEmptyArray() = runTest {
        val client = createClientWithResponse("[]", endpoint = "/historicData")
        val api = SensorAPIImpl(client, baseUrl)

        val result = api.getHistoricSensorReading()
        assertTrue(result.isEmpty())
    }



    @Test
    fun testToggleComponent_returnsOk() = runTest {
        val client = createClientWithResponse("Success", endpoint = "/fan")
        val api = SensorAPIImpl(client, baseUrl)

        val response: HttpResponse = api.toggleComponent(SensorComponent.FAN)
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testToggleComponent_returnsServerError() = runTest {
        val client = createClientWithResponse("Internal Server Error", status = HttpStatusCode.InternalServerError, endpoint = "/fan")
        val api = SensorAPIImpl(client, baseUrl)

        val response: HttpResponse = api.toggleComponent(SensorComponent.FAN)
        assertEquals(HttpStatusCode.InternalServerError, response.status)
    }
}