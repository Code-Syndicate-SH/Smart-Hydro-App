package org.smartroots

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent


class MainApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        Konnection.createInstance(this)

        initKoin() {
            androidLogger()
            androidContext(this@MainApplication)
            platformModule()
        }

    }
}