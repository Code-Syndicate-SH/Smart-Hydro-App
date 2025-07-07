package org.smartroots.data.model

import dev.tmapps.konnection.Konnection
import dev.tmapps.konnection.NetworkConnection

/**
 * @author Shravan Ramjathan
 */
data class NetworkConfigState(
    val currentConnectionStatus: NetworkConnection? = NetworkConnection.UNKNOWN_CONNECTION_TYPE,
    val konnection: Konnection = Konnection.instance
)
