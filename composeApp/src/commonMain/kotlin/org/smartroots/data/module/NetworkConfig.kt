package org.smartroots.data.module

import dev.tmapps.konnection.NetworkConnection
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.smartroots.data.model.NetworkConfigState

/**
 *  @author Shravan Ramjathan
 */
class NetworkConfig() {
    private val _currentConnectionStatus =
        MutableStateFlow<NetworkConfigState>(NetworkConfigState())
    val currentConnectionStatus: StateFlow<NetworkConfigState> =
        _currentConnectionStatus.asStateFlow()


    fun checkConnectionStatus(): NetworkConnection? {
        val currentInstance = _currentConnectionStatus.value.konnection
        _currentConnectionStatus.update { currentState ->
            currentState.copy(currentConnectionStatus = currentInstance.getCurrentNetworkConnection())
        }
        return currentConnectionStatus.value.currentConnectionStatus
    }


}

val networkConfigModule = module {
    singleOf(::NetworkConfig)
}