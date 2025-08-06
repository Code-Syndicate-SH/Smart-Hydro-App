package org.smartroots.data.service

import dev.tmapps.konnection.NetworkConnection

interface NetworkService {


    fun checkNetworkConnectionStatus(): NetworkConnection?
    suspend fun currentIPV4Address(): String?

}