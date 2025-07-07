package org.smartroots.data.repository

import dev.tmapps.konnection.NetworkConnection
import org.smartroots.data.module.NetworkConfig

class NetworkConfigRepositoryImpl(val networkConfig: NetworkConfig){

    fun checkConnectionStatus(): NetworkConnection?{
        return networkConfig.checkConnectionStatus()
    }

    suspend fun checkConnectionInfo():Map<NetworkConnection, String>{
        return networkConfig.checkNetworkInfo()
    }
}