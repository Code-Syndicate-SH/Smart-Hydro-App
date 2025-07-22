package org.smartroots.domain

import org.smartroots.data.model.ArduinoAccess
import org.smartroots.data.model.NetworkUrl
import org.smartroots.data.repository.NetworkConfigRepository

class GetNetworkConnectionUseCase(
    private val networkConfigRepository: NetworkConfigRepository,
    private val localURL: String,
    private val remoteURL: String,
) {
    suspend operator fun invoke(): NetworkUrl? {
        val currentIpv4Address: String? = networkConfigRepository.currentIPV4Address()
        var url: String = ""
        if (currentIpv4Address == null) {
            throw NullPointerException("The system is not connected to a network.")
        }

        url = when {
            currentIpv4Address.startsWith(ArduinoAccess.LOCAL_IP_MATCH.ip) -> localURL
            currentIpv4Address.startsWith(ArduinoAccess.REMOTE_IP_MATCH.ip) -> remoteURL
            else -> "Unknown"
        }
        val isLocal = when {
            url == localURL -> true
            url == remoteURL -> false
            else -> throw NullPointerException("There is an unknown connection!")
        }
        return NetworkUrl(url = url, isLocal = isLocal)
    }
}

