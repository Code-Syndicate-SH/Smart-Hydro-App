package org.smartroots.data.repository.dbRepository

import org.smartroots.data.database.entity.BoxEntity
import org.smartroots.data.relationships.BoxWithSensors

interface BoxRepository {
    suspend fun upsert(box: BoxEntity): Long
    suspend fun getAllBoxes(): List<BoxEntity>
    suspend fun getBoxWithSensors(): List<BoxWithSensors>
}
