package org.smartroots.test

import kotlin.test.*
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import org.smartroots.*

/**
 * Verifies parameterized binding for SensorRepository.
 */
class SensorRepositoryWiringTest : KoinTest {

    @BeforeTest fun before() { stopKoin() }
    @AfterTest  fun after()  { stopKoin() }

    @Test
    fun sensor_repository_resolves_with_baseurl_parameter() {
        startKoin {
            allowOverride(true)
            modules(
                module { }, // empty platform
                // No DB modules here
                sensorRepositoryModule,
                ktorClientModule,
                testOverrides
            )
        }

        val local  = get<org.smartroots.data.repository.SensorRepository> { parametersOf("http://local.test/") }
        val remote = get<org.smartroots.data.repository.SensorRepository> { parametersOf("http://remote.test/") }

        assertNotNull(local)
        assertNotNull(remote)
    }
}
