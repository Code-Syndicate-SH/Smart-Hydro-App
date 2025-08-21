package org.smartroots.data.service

import dev.tmapps.konnection.Konnection
import dev.tmapps.konnection.NetworkConnection
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.compose.getKoin
import org.smartroots.data.model.NetworkConfigState

/**
 *  @author Shravan Ramjathan
 */
class NetworkConfig(val konnection: Konnection) : NetworkService {
    private val _currentConnectionStatus =
        MutableStateFlow<NetworkConfigState>(NetworkConfigState(konnection = konnection))
    val currentConnectionStatus: StateFlow<NetworkConfigState> =
        _currentConnectionStatus.asStateFlow()


    override fun checkNetworkConnectionStatus(): NetworkConnection? {
        val currentInstance = _currentConnectionStatus.value.konnection
        _currentConnectionStatus.update { currentState ->
            currentState.copy(currentConnectionStatus = currentInstance?.getCurrentNetworkConnection())
        }
        return currentConnectionStatus.value.currentConnectionStatus
    }

    override suspend fun currentIPV4Address(): String? {
        val currentNetworkInfo = _currentConnectionStatus.value.konnection?.getInfo()
        val currentIPv4Address = currentNetworkInfo?.ipv4
        return if (currentIPv4Address != null) currentIPv4Address else null
    }


}

