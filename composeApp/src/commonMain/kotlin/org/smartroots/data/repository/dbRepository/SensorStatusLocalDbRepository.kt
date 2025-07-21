package org.smartroots.data.repository.dbRepository

import org.smartroots.data.database.AppDatabase
import org.smartroots.data.database.entity.SensorActivityReading

class SensorStatusLocalDbRepository(private val appDatabase: AppDatabase): SensorStatusRepository {
    override suspend fun insert(sensorActivityReading: SensorActivityReading) {
  return appDatabase.getSensorReadingActivity().insert(sensorActivityReading)
    }

    override suspend fun fetchAllOn(isOn: Boolean): List<SensorActivityReading> {
        return appDatabase.getSensorReadingActivity().fetchAllOn(isOn)
    }
}