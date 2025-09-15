package org.smartroots.test

import org.koin.dsl.module
import org.smartroots.data.service.NetworkConfig
import org.smartroots.data.service.NetworkService
import dev.tmapps.konnection.Konnection

/**
 * Test module to satisfy NetworkConfig's constructor in DI:
 * - Provide a Konnection singleton from the library (no 'new')
 * - Bind NetworkService to NetworkConfig(get())
 */
val konnectionTestModule = module {
    single { Konnection.instance }      // <-- important: use instance, not constructor
    factory<NetworkService> { NetworkConfig(get()) }
}
