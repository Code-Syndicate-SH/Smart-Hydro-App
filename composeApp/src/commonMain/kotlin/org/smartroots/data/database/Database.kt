package org.smartroots.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.smartroots.data.database.Dao.BoxDao
import org.smartroots.data.database.Dao.NoteDao
import org.smartroots.data.database.Dao.SensorDao
import org.smartroots.data.database.Dao.TentDao
import org.smartroots.data.database.entity.BoxEntity
import org.smartroots.data.database.entity.NoteEntity
import org.smartroots.data.database.entity.SensorActivityReading
import org.smartroots.data.database.entity.TentEntity
import org.smartroots.data.utils.Converters

@Database(
    entities = [TentEntity::class, BoxEntity::class, NoteEntity::class, SensorActivityReading::class],
    version = 1
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTentDao(): TentDao
    abstract fun getBoxDao(): BoxDao
    abstract fun getNoteDao(): NoteDao
    abstract fun getSensorReadingActivity(): SensorDao
}
// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT", "EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

fun getRoomDatabase(builder: RoomDatabase.Builder<AppDatabase>): AppDatabase {
    return builder
        .addMigrations()
        .fallbackToDestructiveMigrationOnDowngrade(true)
        .addTypeConverter(Converters)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}