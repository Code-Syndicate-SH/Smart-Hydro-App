package org.smartroots.data.repository.dbRepository

import org.smartroots.data.database.Dao.SensorDao
import org.smartroots.data.database.entity.SensorActivityReading

class SensorStatusRepositoryImpl(private val sensorDao: SensorDao) : SensorStatusRepository {
    override suspend fun insert(sensorActivityReading: SensorActivityReading) {
        return sensorDao.insert(sensorActivityReading)
    }

    override suspend fun fetchAllOn(id: Int): List<SensorActivityReading> {
        return sensorDao.fetchAllOn(id)
    }
}