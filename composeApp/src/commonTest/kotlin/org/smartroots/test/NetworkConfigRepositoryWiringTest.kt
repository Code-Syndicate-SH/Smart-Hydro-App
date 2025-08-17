package org.smartroots.test

import kotlin.test.*
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import org.smartroots.*

/**
 * Ensures NetworkConfigRepository and its NetworkService dependency resolve.
 */
class NetworkConfigRepositoryWiringTest : KoinTest {

    @BeforeTest fun before() { stopKoin() }
    @AfterTest  fun after()  { stopKoin() }

    @Test
    fun network_config_repository_resolves() {
        startKoin {
            allowOverride(true)
            modules(
                networkConfigModule,
                networkRepositoryModule,
                testOverrides
            )
        }

        assertNotNull(get<org.smartroots.data.repository.NetworkConfigRepository>())
    }
}
