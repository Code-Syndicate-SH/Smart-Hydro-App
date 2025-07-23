package org.smartroots

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import org.smartroots.data.database.AppDatabase

 fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<AppDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("room.db")
    return Room.databaseBuilder<AppDatabase>(context = appContext, name = dbFile.absolutePath)
}