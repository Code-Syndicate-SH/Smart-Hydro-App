package org.smartroots.data.repository.dbRepository

import org.smartroots.data.database.Dao.SensorDao
import org.smartroots.data.database.entity.SensorActivityReading

class SensorStatusRepositoryImpl(private val sensorDao: SensorDao) : SensorStatusRepository {
    override suspend fun upsert(sensorActivityReading: SensorActivityReading): Long {
        return sensorDao.upsert(sensorActivityReading)
    }

    override suspend fun getSensorStatus(id: Int): SensorActivityReading {
        return sensorDao.getSensorStatus(id)
    }

    override suspend fun fetchAllOn(): List<SensorActivityReading> {
        return sensorDao.fetchAllOn()
    }

    override suspend fun getAllSensorStatus(): List<SensorActivityReading> {
        return sensorDao.getAllSensorActivities()
    }
}