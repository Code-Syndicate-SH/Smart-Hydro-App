package org.smartroots.data.database.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.smartroots.data.database.entity.SensorActivityReading

@Dao
interface SensorDao {

    @Insert
    suspend fun insert(sensorActivityReading: SensorActivityReading)

    @Query("SELECT * FROM sensor_activity WHERE sensor_activity.isOn =true AND sensor_activity.sensorId = :id ")
    suspend fun fetchAllOn(id: Int): List<SensorActivityReading>

    @Insert
    suspend fun getAllSensorActivities(): List<SensorActivityReading>

}