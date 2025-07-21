package org.smartroots.data.database.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.smartroots.data.database.entity.TentEntity
@Dao
interface TentDao {
    @Insert
    suspend fun insert(item: TentEntity)

    @Query("SELECT * FROM tent")
    suspend fun getAllTents(): List<TentEntity>

    @Query("SELECT * FROM  tent WHERE tent.name=:name")
    suspend fun getTentWithName(name: String): TentEntity
}