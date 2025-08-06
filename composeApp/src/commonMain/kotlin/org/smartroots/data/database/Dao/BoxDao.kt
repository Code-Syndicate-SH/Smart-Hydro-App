package org.smartroots.data.database.Dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import org.smartroots.data.database.entity.BoxEntity
import org.smartroots.data.relationships.BoxWithSensors

@Dao
interface BoxDao {

    @Upsert
    suspend fun upsert(box: BoxEntity): Long

    @Query("SELECT * FROM box")
    suspend fun getAllBoxes(): List<BoxEntity>

    @Transaction
    @Query("SELECT * FROM box")
    suspend fun getAllBoxSensors(): List<BoxWithSensors>

}