package org.smartroots.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SensorActivityReading(
    @PrimaryKey (autoGenerate = true)  val sensorId:Int,
    val sensorName:String,
    val isOn:Boolean,
)
