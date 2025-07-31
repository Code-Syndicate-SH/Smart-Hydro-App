package org.smartroots.data.repository.dbRepository

import org.smartroots.data.database.entity.SensorActivityReading

interface SensorStatusRepository {
    suspend fun upsert(sensorActivityReading: SensorActivityReading): Long
    suspend fun getSensorStatus(id:Int): SensorActivityReading
    suspend fun fetchAllOn(): List<SensorActivityReading>
    suspend fun getAllSensorStatus():List<SensorActivityReading>
}
