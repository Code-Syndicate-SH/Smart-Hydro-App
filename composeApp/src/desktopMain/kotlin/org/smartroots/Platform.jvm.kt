package org.smartroots

import androidx.room.RoomDatabase
import org.koin.dsl.module
import org.smartroots.data.database.AppDatabase

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()
actual fun platformModule() = module {
    single<RoomDatabase.Builder<AppDatabase>> {
        getDatabaseBuilder()
    }
}