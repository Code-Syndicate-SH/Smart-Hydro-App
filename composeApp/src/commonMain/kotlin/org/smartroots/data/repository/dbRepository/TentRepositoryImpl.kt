package org.smartroots.data.repository.dbRepository

import org.smartroots.data.database.Dao.TentDao
import org.smartroots.data.database.entity.TentEntity
import org.smartroots.data.relationships.TentWithBoxes

class TentRepositoryImpl(private val tentDao: TentDao) : TentRepository {
    override suspend fun getTentWithName(name: String): TentEntity {
        return tentDao.getTentWithName(name)
    }

    override suspend fun getAllTents(): List<TentEntity> {
        return tentDao.getAllTents()
    }

    override suspend fun getTentWithBoxes(): List<TentWithBoxes> {
        return tentDao.getTentWithBoxes()
    }
}