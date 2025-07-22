package org.smartroots.data.repository.dbRepository

import org.smartroots.data.database.Dao.BoxDao
import org.smartroots.data.database.entity.BoxEntity
import org.smartroots.data.relationships.BoxWithSensors

// remote database set up
class BoxRepositoryImpl(private val boxDao: BoxDao) : BoxRepository {
    override suspend fun insert(box: BoxEntity): Long {
        return boxDao.insert(box)
    }

    override suspend fun getAllBoxes(): List<BoxEntity> {
        return boxDao.getAllBoxes()
    }

    override suspend fun getBoxWithSensors(): List<BoxWithSensors> {
        return boxDao.getAllBoxSensors()
    }
}