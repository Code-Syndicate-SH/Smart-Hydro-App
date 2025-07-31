package org.smartroots.domain.database.usecase

import org.smartroots.data.database.entity.SensorActivityReading
import org.smartroots.data.repository.dbRepository.SensorStatusRepository

class SaveSensorStatusUseCase(private val sensorStatusRepository: SensorStatusRepository) {
    suspend operator fun invoke(sensorActivityReading: SensorActivityReading): SensorActivityReading? {
        if (sensorActivityReading.sensorName.isEmpty()) {
            return null // if this happens the ui was not strict enough
        }
        try {
            val id = sensorStatusRepository.upsert(sensorActivityReading).toInt()
            val sensorStatus: SensorActivityReading = sensorStatusRepository.getSensorStatus(id)
            return sensorStatus
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}