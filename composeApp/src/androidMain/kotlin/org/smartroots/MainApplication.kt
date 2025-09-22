package org.smartroots

import android.app.Application
import dev.tmapps.konnection.Konnection
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent


class MainApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        val konnection = Konnection.createInstance(context = this)
        initKoin() {
            androidLogger()
            androidContext(this@MainApplication)
            platformModule()
        }

    }
}