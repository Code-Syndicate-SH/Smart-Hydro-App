package org.smartroots.data.database.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.smartroots.data.database.entity.SensorActivityReading

@Dao
interface SensorDao {

    @Insert
    suspend fun insert(sensorActivityReading: SensorActivityReading): Long
    @Query("SELECT * FROM sensor_activity WHERE sensorId = :id")
    suspend fun getSensorStatus(id:Int): SensorActivityReading

    @Query("SELECT * FROM sensor_activity WHERE sensor_activity.isOn =true")
    suspend fun fetchAllOn(): List<SensorActivityReading>

    @Query("SELECT * FROM sensor_activity")
    suspend fun getAllSensorActivities(): List<SensorActivityReading>

}