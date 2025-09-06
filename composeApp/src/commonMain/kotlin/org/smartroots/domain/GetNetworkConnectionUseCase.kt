package org.smartroots.domain

import org.smartroots.data.model.ArduinoAccess
import org.smartroots.data.model.NetworkUrl
import org.smartroots.data.repository.NetworkConfigRepository

class GetNetworkConnectionUseCase(
    private val networkConfigRepository: NetworkConfigRepository,

) {

    suspend operator fun invoke(): NetworkUrl? {
        val currentIpv4Address: String? = networkConfigRepository.currentIPV4Address()
       val isLocalConnection = currentIpv4Address==null

        val url = if(isLocalConnection) ArduinoAccess.LOCAL_IP.ip else ArduinoAccess.REMOTE_IP.ip

        return NetworkUrl(url =url , isLocal = isLocalConnection)
    }
}

