package org.smartroots.data.repository.dbRepository

import org.smartroots.data.database.AppDatabase
import org.smartroots.data.database.entity.BoxEntity
import org.smartroots.data.relationships.BoxWithSensors

class BoxLocalDbRepository(private val appDatabase: AppDatabase): BoxRepository {
    override suspend fun insert(box: BoxEntity) {
     return appDatabase.getBoxDao().insert(box)
    }

    override suspend fun getAllBoxes(): List<BoxEntity> {
        return appDatabase.getBoxDao().getAllBoxes()
    }

    override suspend fun getBoxWithSensors(): List<BoxWithSensors> {
        return appDatabase.getBoxDao().getAllBoxSensors()
    }
}