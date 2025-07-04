package org.smartroots.data.module

import de.jensklingenberg.ktorfit.Ktorfit

import org.koin.dsl.module

import org.smartroots.data.service.SensorReading


private const val BASE_URL = "http://192.168.8.14/"


    fun provideLocalKtorFit(): Ktorfit {
        return Ktorfit.Builder().baseUrl(BASE_URL).build()
    }
    fun provideLocalSensorReading(ktorfit: Ktorfit): SensorReading{
        return ktorfit.create()
    }
    val ktorfitReadingModule = module {
        single{provideLocalKtorFit()}
        single { provideLocalSensorReading(get())  }
    }




