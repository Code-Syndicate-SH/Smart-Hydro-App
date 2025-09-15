package org.smartroots.test

import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.smartroots.data.model.Sensor
import org.smartroots.data.service.SensorAPI
import org.smartroots.data.service.SensorComponent
import io.ktor.client.statement.HttpResponse

/**
 * Minimal overrides for DI tests:
 * - Param-accepting SensorAPI so SensorRepository can resolve.
 * - Stable BASE_URLs for tests.
 * No DB bindings here (keeps commonTest portable).
 */
private class FakeSensorApi(private val baseUrl: String) : SensorAPI {
    override suspend fun getSensorReading(): Sensor {
        // Not executed in wiring tests (we only resolve the graph).
        throw UnsupportedOperationException("Not executed in DI wiring tests: baseUrl=$baseUrl")
    }
    override suspend fun getHistoricSensorReading(): List<Sensor> = emptyList()
    override suspend fun toggleComponent(component: SensorComponent): HttpResponse {
        throw UnsupportedOperationException("Not executed in DI wiring tests")
    }
}

val testOverrides = module {
    // Your production code uses parametersOf(baseURL) to resolve SensorAPI
    factory<SensorAPI> { (baseURL: String) -> FakeSensorApi(baseURL) }

    // Provide stable base URLs for tests (override your module if needed)
    single(named("BASE_URL_LOCAL")) { "http://local.test/" }
    single(named("BASE_URL_REMOTE")) { "http://remote.test/" }
}
