package org.smartroots.data.model

import dev.tmapps.konnection.Konnection
import dev.tmapps.konnection.NetworkConnection


data class NetoworkConfigState(
    val currentConnectionStatus: NetworkConnection,
    val konnection: Konnection
)
