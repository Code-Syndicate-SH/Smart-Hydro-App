package org.smartroots.domain

import org.smartroots.data.repository.NetworkConfigRepository
import org.smartroots.data.repository.SensorRepository

class GetSensorReadingsUseCase(private val sensorRepository: SensorRepository, private val networkConfigRepository: NetworkConfigRepository) {

}