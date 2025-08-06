package org.smartroots.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sensor_activity")
data class SensorActivityReading(
    @PrimaryKey (autoGenerate = true)  val sensorId:Int = 0,
    val sensorName:String,
    val isOn:Boolean,
    val boxId:Int
)
