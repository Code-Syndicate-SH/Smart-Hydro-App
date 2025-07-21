package org.smartroots.data.repository.dbRepository

import org.smartroots.data.database.entity.SensorActivityReading

interface SensorStatusRepository {
    suspend fun insert(sensorActivityReading: SensorActivityReading)
    suspend fun fetchAllOn(isOn: Boolean = true): List<SensorActivityReading>
}