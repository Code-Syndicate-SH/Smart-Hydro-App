package org.smartroots.data.database.Dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import org.smartroots.data.database.entity.SensorActivityReading

@Dao
interface SensorDao {

    @Upsert
    suspend fun upsert(sensorActivityReading: SensorActivityReading): Long

    @Query("SELECT * FROM sensor_activity WHERE sensorId = :id")
    suspend fun getSensorStatus(id: Int): SensorActivityReading

    @Query("SELECT * FROM sensor_activity WHERE sensor_activity.isOn =true")
    suspend fun fetchAllOn(): List<SensorActivityReading>

    @Query("SELECT * FROM sensor_activity")
    suspend fun getAllSensorActivities(): List<SensorActivityReading>

}