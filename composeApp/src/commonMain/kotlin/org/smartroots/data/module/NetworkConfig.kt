package org.smartroots.data.module

import dev.tmapps.konnection.Konnection
import dev.tmapps.konnection.NetworkConnection
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.getScopeId

class NetworkConfig() {
    private val currentConnectionStatus =
        MutableStateFlow<NetworkConnection>(NetworkConnection.UNKNOWN_CONNECTION_TYPE)
    private val  konnection:Konnection = Konnection.instance
    init {
        checkConnectionStatus()
    }

    fun checkConnectionStatus(): NetworkConnection{
     TODO()
    }

}