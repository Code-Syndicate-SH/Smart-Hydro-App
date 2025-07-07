package org.smartroots.data.module

import dev.tmapps.konnection.ConnectionInfo
import dev.tmapps.konnection.NetworkConnection
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.smartroots.data.model.NetworkConfigState

class NetworkConfig() {
    private val _currentConnectionStatus =
        MutableStateFlow<NetworkConfigState>(NetworkConfigState())
    val currentConnectionStatus: StateFlow<NetworkConfigState> =
        _currentConnectionStatus.asStateFlow()

    init {
        checkConnectionStatus()
    }

    fun checkConnectionStatus(): NetworkConnection? {
        val currentInstance = _currentConnectionStatus.value.konnection
        _currentConnectionStatus.update { currentState ->
            currentState.copy(currentConnectionStatus = currentInstance.getCurrentNetworkConnection())
        }
        return currentConnectionStatus.value.currentConnectionStatus
    }

    private fun getConnectionType(): NetworkConnection? {
        return _currentConnectionStatus.value.currentConnectionStatus
    }
    private fun determineNetworkLabel(ipv4:String):String{
        return when{

            ipv4.startsWith("192.168.8.")-> "Local"
            ipv4.startsWith("192.168.1.")-> "Remote"
            else-> "Unknown"
        }
    }
    suspend fun checkNetworkInfo(): Map<NetworkConnection, String> {
        val connection = _currentConnectionStatus.value.konnection
        val connectionType = getConnectionType()
        val currentConnectionMap: MutableMap<NetworkConnection, String> = mutableMapOf(
            NetworkConnection.UNKNOWN_CONNECTION_TYPE to "Unknown connection"
        )   // this will hold what the user is on, and the address
        if (connectionType == NetworkConnection.BLUETOOTH_TETHERING) {
            mapOf(NetworkConnection.BLUETOOTH_TETHERING to "Bluetooth")
        } else if(connectionType==null) {
            currentConnectionMap
        }
        val connectionInfo: ConnectionInfo? = connection.getInfo()
        if (connectionInfo == null) {
            return currentConnectionMap   // returns empty map
        }
        val ipv4Address: String? = connection.getInfo()?.externalIp
        if (ipv4Address == null) {
            return currentConnectionMap
        }

        return currentConnectionMap
    }

    // rememeber to do dependency injection

}

val networkConfigModule = module {
    singleOf(::NetworkConfig)
}