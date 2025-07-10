package org.smartroots.domain

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.smartroots.data.repository.NetworkConfigRepository
import org.smartroots.data.repository.SensorRepository

class GetSensorReadingsUseCase(private val sensorRepository: SensorRepository, private val networkConfigRepository: NetworkConfigRepository) {

}
val GetSensorReadingsUseCaseModule = module{
    factoryOf(::GetSensorReadingsUseCase)
}