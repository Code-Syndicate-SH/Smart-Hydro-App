package org.smartroots.data.repository

import dev.tmapps.konnection.NetworkConnection
import org.smartroots.data.model.NetworkInfo

interface NetworkConfigRepository {
    fun checkConnectionStatus(): NetworkConnection?
    suspend fun checkNetworkInfo(): NetworkInfo
}