package org.smartroots.data.database.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.smartroots.data.database.entity.SensorActivityReading
@Dao
interface SensorDao {

    @Insert
    suspend fun insert(sensorActivityReading: SensorActivityReading)

    @Query("SELECT * FROM sensor WHERE sensor.isOn =:isOn ")
    suspend fun fetchAllOn(isOn: Boolean = true)
}