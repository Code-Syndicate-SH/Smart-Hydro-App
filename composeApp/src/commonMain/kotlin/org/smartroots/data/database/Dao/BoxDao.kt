package org.smartroots.data.database.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import org.smartroots.data.database.entity.BoxEntity
import org.smartroots.data.relationships.BoxWithSensors

@Dao
interface BoxDao {

    @Insert
    suspend fun insert(box: BoxEntity): Long

    @Query("SELECT * FROM box")
    suspend fun getAllBoxes(): List<BoxEntity>

    @Transaction
    @Query("SELECT * FROM box")
    suspend fun getAllBoxSensors(): List<BoxWithSensors>

}