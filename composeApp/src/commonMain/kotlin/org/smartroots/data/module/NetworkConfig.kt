package org.smartroots.data.module

import dev.tmapps.konnection.NetworkConnection
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.smartroots.data.model.NetworkConfigState

class NetworkConfig() {
    private val _currentConnectionStatus =
        MutableStateFlow<NetworkConfigState>(NetworkConfigState())
    val currentConnectionState: StateFlow<NetworkConfigState> =_currentConnectionStatus.asStateFlow()
        init {
            checkConnectionStatus()
        }

    fun checkConnectionStatus(): NetworkConnection? {
        val currentInstance = _currentConnectionStatus.value.konnection
        _currentConnectionStatus.update { currentState ->
            currentState.copy(currentConnectionStatus = currentInstance.getCurrentNetworkConnection())
        }
     return currentConnectionState.value.currentConnectionStatus
    }

}