package org.smartroots.data.service

import de.jensklingenberg.ktorfit.http.GET
import org.koin.dsl.module
import org.smartroots.data.model.Sensor

interface SensorReading {

        @GET("sdfsd")
        suspend fun getSensorData(): Sensor

        //The endpoint for the second url is declared

        @GET("getHistoricData")
        suspend fun getHistoricData(): Sensor  // build  up on this

}
