package org.smartroots.test

import kotlin.test.*
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import org.smartroots.*

/**
 * Validates DI wiring for services/repos/use cases without Room/Android.
 */
class KoinGraphSmokeTest : KoinTest {

    @BeforeTest fun before() { stopKoin() }
    @AfterTest  fun after()  { stopKoin() }

    @Test
    fun
            di_graph_resolves_core_components_without_db() {
        val emptyPlatform = module { }

        startKoin {
            allowOverride(true)
            modules(
                emptyPlatform,
                // Omit DB modules in commonTest:
                // dbDatabaseDao,
                // provideDatabaseRepository,

                // App modules that don't require Room:
                sensorRepositoryModule,
                networkConfigModule,
                networkRepositoryModule,
                NetworkConnectionUseCaseModule,
                GetSensorReadingsUseCaseModule,
                // homeViewModelModule, // enable when you want to resolve the VM too
                ktorClientModule,

                // Test overrides last
                testOverrides
            )
        }

        assertNotNull(get<org.smartroots.data.service.NetworkService>())
        assertNotNull(get<org.smartroots.data.repository.NetworkConfigRepository>())
        assertNotNull(get<org.smartroots.domain.GetNetworkConnectionUseCase>())
        assertNotNull(get<org.smartroots.domain.GetSensorReadingsUseCase>())
    }
}
