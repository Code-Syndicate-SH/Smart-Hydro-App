package org.smartroots

import androidx.room.RoomDatabase
import getDatabaseBuilder
import org.koin.dsl.module
import org.smartroots.data.database.AppDatabase
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun platformModule() = module {
    single<RoomDatabase.Builder<AppDatabase>> {
     getDatabaseBuilder()
    }
}