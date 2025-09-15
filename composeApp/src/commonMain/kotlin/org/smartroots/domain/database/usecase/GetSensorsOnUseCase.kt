package org.smartroots.domain.database.usecase

import org.smartroots.data.database.entity.SensorActivityReading
import org.smartroots.data.repository.dbRepository.SensorStatusRepository

class GetSensorsOnUseCase(
    val id: Int,
    private val sensorStatusRepository: SensorStatusRepository,
) {

    suspend operator fun invoke(): List<SensorActivityReading> {
        return try {
            sensorStatusRepository.fetchAllOn()
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}
