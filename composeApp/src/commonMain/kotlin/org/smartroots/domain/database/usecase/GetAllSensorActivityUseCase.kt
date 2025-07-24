package org.smartroots.domain.database.usecase

import org.smartroots.data.database.entity.SensorActivityReading
import org.smartroots.data.repository.dbRepository.SensorStatusRepository

class GetAllSensorActivityUseCase(private val sensorStatusRepository: SensorStatusRepository) {
    suspend operator fun invoke(): List<SensorActivityReading> {
        return try {
            sensorStatusRepository.getAllSensorStatus()
        } catch (e: Exception) {
            throw Exception(e) // add a custom exception
        }
    }
}