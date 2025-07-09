package org.smartroots.data.repository

import dev.tmapps.konnection.NetworkConnection
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.smartroots.data.model.NetworkInfo
import org.smartroots.data.module.NetworkConfig
/**
 * @author Shravan Ramjathan
 */
class NetworkConfigRepositoryImpl(val networkConfig: NetworkConfig): NetworkConfigRepository{

   override fun checkConnectionStatus(): NetworkConnection?{
        return networkConfig.checkConnectionStatus()
    }

    override suspend fun checkNetworkInfo(): NetworkInfo {
        return networkConfig.checkNetworkInfo()
    }

}
val networkRepositoryModule = module {
    factory<NetworkConfigRepository>{ NetworkConfigRepositoryImpl(get()) }
}