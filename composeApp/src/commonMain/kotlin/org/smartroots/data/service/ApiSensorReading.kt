package org.smartroots.data.service

import org.smartroots.data.model.Sensor

interface ApiSensorReading {
   suspend fun getSensorReading(isLocal:Boolean): Sensor
}