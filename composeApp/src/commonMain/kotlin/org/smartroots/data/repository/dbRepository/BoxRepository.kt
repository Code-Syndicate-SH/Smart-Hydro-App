package org.smartroots.data.repository.dbRepository

import org.smartroots.data.database.entity.BoxEntity
import org.smartroots.data.relationships.BoxWithSensors

interface BoxRepository {
    suspend fun insert(box: BoxEntity)
    suspend fun getAllBoxes():List<BoxEntity>
    suspend fun getBoxWithSensors():List<BoxWithSensors>
}