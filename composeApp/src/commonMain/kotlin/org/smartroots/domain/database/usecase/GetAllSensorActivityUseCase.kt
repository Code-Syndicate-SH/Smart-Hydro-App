package org.smartroots.domain.database.usecase

import org.smartroots.data.database.Dao.SensorDao
import org.smartroots.data.database.entity.SensorActivityReading

class GetAllSensorActivityUseCase(private val sensorDao: SensorDao) {
    suspend operator  fun invoke():List<SensorActivityReading>{
        return try{
            sensorDao.getAllSensorActivities()
        }
        catch(e: Exception){
            throw Exception(e) // add a custom exception
        }
    }
}