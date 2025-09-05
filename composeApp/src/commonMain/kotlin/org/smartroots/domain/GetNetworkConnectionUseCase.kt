package org.smartroots.domain

import org.smartroots.data.model.ArduinoAccess
import org.smartroots.data.model.NetworkUrl
import org.smartroots.data.repository.NetworkConfigRepository

class GetNetworkConnectionUseCase(
    private val networkConfigRepository: NetworkConfigRepository,

) {
    val BASE_URL_LOCAL =   "http://192.168.8.14/"
    val BASE_URL_REMOTE =   "http://192.168.1.102/"
    suspend operator fun invoke(): NetworkUrl? {
        val currentIpv4Address: String? = networkConfigRepository.currentIPV4Address()
        if (currentIpv4Address == null) {
            throw NullPointerException("The system is not connected to a network.")
        }

        val url = when {
            currentIpv4Address.startsWith(ArduinoAccess.LOCAL_IP_MATCH.ip) -> BASE_URL_LOCAL
            currentIpv4Address.startsWith(ArduinoAccess.REMOTE_IP_MATCH.ip) -> BASE_URL_REMOTE
            else -> "Unknown"
        }
        val isLocal = when {
            url == BASE_URL_LOCAL -> true
            url == BASE_URL_REMOTE -> false
            else -> true
        }
        return NetworkUrl(url = BASE_URL_LOCAL, isLocal = isLocal)
    }
}

