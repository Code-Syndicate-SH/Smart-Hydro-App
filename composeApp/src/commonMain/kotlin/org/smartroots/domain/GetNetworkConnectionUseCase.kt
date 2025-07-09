package org.smartroots.domain

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.smartroots.data.model.NetworkInfo
import org.smartroots.data.repository.NetworkConfigRepository

class GetNetworkConnectionUseCase(
    private val networkConfigRepository: NetworkConfigRepository,
    private val localURL: String,
    private val remoteURL: String,
) {
    suspend operator fun invoke(): String {
        val networkInfo: NetworkInfo = networkConfigRepository.checkNetworkInfo()

        val result =  when (networkInfo.urlUsage) {
            "Remote" -> remoteURL
            "Local"-> localURL
         else -> "Unknown"   // throw an error here, make a custom error, also just make sure to verify if it recognises, bluetooth and wifi at the same time
        }
        return result
    }
}
val NetworkConnectionUseCaseModule = module{
    factoryOf(::GetNetworkConnectionUseCase)
}