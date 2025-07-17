package org.smartroots.domain

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.smartroots.data.model.ArduinoAccess
import org.smartroots.data.repository.NetworkConfigRepository

class GetNetworkConnectionUseCase(
    private val networkConfigRepository: NetworkConfigRepository,
    private val localURL: String,
    private val remoteURL: String,
) {
    suspend operator fun invoke(): String? {
        val currentIpv4Address: String? = networkConfigRepository.currentIPV4Address()
        var urlUsage: String = ""
        if (currentIpv4Address == null) {
            throw NullPointerException("The system is not connected to a network.")
        }
        urlUsage = when {
            currentIpv4Address.startsWith(ArduinoAccess.LOCAL_IP_MATCH.ip) -> localURL
            currentIpv4Address.startsWith(ArduinoAccess.REMOTE_IP_MATCH.ip) -> remoteURL
            else -> "Unknown"
        }
        return urlUsage
    }
}

val NetworkConnectionUseCaseModule = module {
    factoryOf(::GetNetworkConnectionUseCase)
}