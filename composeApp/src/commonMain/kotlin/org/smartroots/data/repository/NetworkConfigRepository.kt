package org.smartroots.data.repository

import dev.tmapps.konnection.NetworkConnection

interface NetworkConfigRepository {
    fun checkConnectionStatus(): NetworkConnection?
    suspend fun checkNetworkInfo(): Map<NetworkConnection,String>
}