package org.smartroots

import android.os.Build
import androidx.room.RoomDatabase
import org.koin.dsl.module
import org.smartroots.data.database.AppDatabase

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()
actual fun platformModule() = module {
    single<RoomDatabase.Builder<AppDatabase>> {
        getDatabaseBuilder(get())
    }
}
