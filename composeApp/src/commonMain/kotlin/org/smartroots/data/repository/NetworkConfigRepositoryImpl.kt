package org.smartroots.data.repository

import dev.tmapps.konnection.NetworkConnection
import org.koin.dsl.module
import org.smartroots.data.service.NetworkConfig

/**
 * @author Shravan Ramjathan
 */
class NetworkConfigRepositoryImpl(val networkConfig: NetworkConfig) : NetworkConfigRepository {

    override fun checkConnectionStatus(): NetworkConnection? {
        return networkConfig.checkNetworkConnectionStatus()
    }

    override suspend fun currentIPV4Address(): String? {
        return networkConfig.currentIPV4Address()
    }

}

val networkRepositoryModule = module {
    factory<NetworkConfigRepository> { NetworkConfigRepositoryImpl(get()) }
}