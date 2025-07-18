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
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import org.smartroots.data.service.SensorComponent
import kotlin.test.assertTrue
import kotlin.test.fail



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

    @Test
    fun testGetHistoricSensorReading_returnsSensorList() = runTest {
        val sensors = listOf(
            Sensor(eC = "2.1", humidity = "65", light = "250", pH = "6.7", temperature = "24", flowRate = "1.0"),
            Sensor(eC = "2.3", humidity = "63", light = "240", pH = "6.8", temperature = "25", flowRate = "1.1")
        )
        val jsonResponse = Json.encodeToString(ListSerializer(Sensor.serializer()), sensors)

        val mockEngine = MockEngine { request ->
            respond(jsonResponse, HttpStatusCode.OK, headersOf(HttpHeaders.ContentType, "application/json"))
        }

        val client = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }

        val api = SensorAPIImpl(client, "http://localhost")
        val result = api.getHistoricSensorReading()

        assertEquals(sensors, result)
    }
    @Test
    fun testToggleLight_returnsOk() = runTest {
        val mockEngine = MockEngine { request ->
            respond("Light toggled", HttpStatusCode.OK)
        }

        val client = HttpClient(mockEngine)
        val api = SensorAPIImpl(client, "http://localhost")
        val response = api.toggleComponent(SensorComponent.LIGHT)

        assertEquals(HttpStatusCode.OK, response.status)
    }
    @Test
    fun testToggleFan_returnsOk() = runTest {
        val client = HttpClient(MockEngine) {
            engine {
                addHandler {
                    respond("Fan toggled", HttpStatusCode.OK)
                }
            }
        }
        val api = SensorAPIImpl(client, "http://localhost")
        val response = api.toggleComponent(SensorComponent.FAN)
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testTogglePump_returnsOk() = runTest {
        val client = HttpClient(MockEngine) {
            engine {
                addHandler {
                    respond("Pump toggled", HttpStatusCode.OK)
                }
            }
        }
        val api = SensorAPIImpl(client, "http://localhost")
        val response = api.toggleComponent(SensorComponent.PUMP)
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testEc_returnsOk() = runTest {
        val client = HttpClient(MockEngine) {
            engine {
                addHandler {
                    respond("EC read", HttpStatusCode.OK)
                }
            }
        }
        val api = SensorAPIImpl(client, "http://localhost")
        val response = api.toggleComponent(SensorComponent.EC)
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testEcUp_returnsOk() = runTest {
        val client = HttpClient(MockEngine) {
            engine {
                addHandler {
                    respond("EC up", HttpStatusCode.OK)
                }
            }
        }
        val api = SensorAPIImpl(client, "http://localhost")
        val response = api.toggleComponent(SensorComponent.EC_UP)
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testEcDown_returnsOk() = runTest {
        val client = HttpClient(MockEngine) {
            engine {
                addHandler {
                    respond("EC down", HttpStatusCode.OK)
                }
            }
        }
        val api = SensorAPIImpl(client, "http://localhost")
        val response = api.toggleComponent(SensorComponent.EC_DOWN)
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testPh_returnsOk() = runTest {
        val client = HttpClient(MockEngine) {
            engine {
                addHandler {
                    respond("pH read", HttpStatusCode.OK)
                }
            }
        }
        val api = SensorAPIImpl(client, "http://localhost")
        val response = api.toggleComponent(SensorComponent.PH)
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testPhUp_returnsOk() = runTest {
        val client = HttpClient(MockEngine) {
            engine {
                addHandler {
                    respond("pH up", HttpStatusCode.OK)
                }
            }
        }
        val api = SensorAPIImpl(client, "http://localhost")
        val response = api.toggleComponent(SensorComponent.PH_UP)
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testGetSensorReading_withEmptyJson_shouldReturnDefaultSensor() = runTest {
        val client = createMockClient("{}") // empty JSON
        val api = SensorAPIImpl(client, "http://localhost")

        val result = api.getSensorReading()

        assertEquals("", result.eC)
        assertEquals("", result.humidity)
        assertEquals("", result.light)
        assertEquals("", result.pH)
        assertEquals("", result.temperature)
        assertEquals("", result.flowRate)
    }

    @Test
    fun testGetSensorReading_withMissingFields_shouldDefaultToEmpty() = runTest {
        val json = """{ "EC": "2.0", "PH": "5.5" }"""
        val client = createMockClient(json)
        val api = SensorAPIImpl(client, "http://localhost")

        val result = api.getSensorReading()

        assertEquals("2.0", result.eC)
        assertEquals("5.5", result.pH)
        assertEquals("", result.humidity) // missing
        assertEquals("", result.light)
        assertEquals("", result.temperature)
        assertEquals("", result.flowRate)
    }

    @Test
    fun testGetSensorReading_withMalformedJson_shouldThrow() = runTest {
        val malformedJson = """{ "EC": 1.5, "Humidity": "bad }""" // broken JSON
        val client = createMockClient(malformedJson)
        val api = SensorAPIImpl(client, "http://localhost")

        try {
            api.getSensorReading()
            fail("Expected SerializationException but none was thrown")
        } catch (e: Exception) {
            assertTrue(e is kotlinx.serialization.SerializationException)
        }
    }


    @Test
    fun testToggleLight_withServerError_shouldReturnErrorStatus() = runTest {
        val client = HttpClient(MockEngine) {
            engine {
                addHandler {
                    respond("Internal Server Error", HttpStatusCode.InternalServerError)
                }
            }
        }
        val api = SensorAPIImpl(client, "http://localhost")
        val response = api.toggleComponent(SensorComponent.LIGHT)

        assertEquals(HttpStatusCode.InternalServerError, response.status)
    }





}
