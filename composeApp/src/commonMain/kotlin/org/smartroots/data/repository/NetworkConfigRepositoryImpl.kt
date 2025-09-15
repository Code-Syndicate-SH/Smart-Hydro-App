package org.smartroots.data.repository

import dev.tmapps.konnection.NetworkConnection
import org.smartroots.data.service.NetworkConfig
import org.smartroots.data.service.NetworkService

/**
 * @author Shravan Ramjathan
 */
class NetworkConfigRepositoryImpl(val networkService: NetworkService) : NetworkConfigRepository {

    override fun checkConnectionStatus(): NetworkConnection? {
        return networkService.checkNetworkConnectionStatus()
    }

    override suspend fun currentIPV4Address(): String? {
        return networkService.currentIPV4Address()
    }

}

