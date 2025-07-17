package org.smartroots.data

import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.smartroots.data.model.Sensor
import org.smartroots.data.service.SensorAPIImpl
import kotlin.test.Test
import kotlin.test.assertEquals

class SensorApiTest {

    private val mockJson = """
        {
            "EC": "1.5",
            "Humidity": "70",
            "Light": "300",
            "PH": "6.0",
            "Temperature": "24",
            "FlowRate": "2.5"
        }
    """.trimIndent()

    private fun createMockClient(jsonResponse: String): HttpClient {
        return HttpClient(MockEngine) {
            engine {
                addHandler { request ->
                    when (request.url.encodedPath) {
                        "/r/n/r/n" -> respond(
                            content = jsonResponse,
                            status = HttpStatusCode.OK,
                            headers = headersOf(HttpHeaders.ContentType, "application/json")
                        )
                        else -> error("Unhandled URL: ${request.url.encodedPath}")
                    }
                }
            }
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }
    }

    @Test
    fun testGetSensorReading_returnsValidSensor() = runTest {
        val client = createMockClient(mockJson)
        val api = SensorAPIImpl(tentClient = client, baseURL = "http://localhost")

        val result: Sensor = api.getSensorReading()

        assertEquals("1.5", result.eC)
        assertEquals("70", result.humidity)
        assertEquals("300", result.light)
        assertEquals("6.0", result.pH)
        assertEquals("24", result.temperature)
        assertEquals("2.5", result.flowRate)
    }
}
