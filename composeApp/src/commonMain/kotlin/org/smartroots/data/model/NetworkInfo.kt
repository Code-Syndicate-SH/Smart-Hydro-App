package org.smartroots.data.model

import dev.tmapps.konnection.NetworkConnection

data class NetworkInfo(
    val networkType: NetworkConnection? =null,
    val urlUsage:String = ""
)
