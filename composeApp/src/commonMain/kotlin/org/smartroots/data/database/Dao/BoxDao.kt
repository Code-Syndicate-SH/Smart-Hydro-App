package org.smartroots.data.database.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.smartroots.data.database.entity.BoxEntity
@Dao
interface BoxDao {

    @Insert
    suspend fun insert(box: BoxEntity)

    @Query("SELECT * FROM box")
    suspend fun getAllBoxes():List<BoxEntity>

}