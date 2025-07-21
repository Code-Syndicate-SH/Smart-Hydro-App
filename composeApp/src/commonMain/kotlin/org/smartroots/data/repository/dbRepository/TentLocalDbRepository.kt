package org.smartroots.data.repository.dbRepository

import org.smartroots.data.database.AppDatabase
import org.smartroots.data.database.entity.TentEntity
import org.smartroots.data.relationships.TentWithBoxes

class TentLocalDbRepository(private val appDatabase: AppDatabase): TentRepository {
    override suspend fun getTentWithName(name: String): TentEntity {
        return appDatabase.getTentDao().getTentWithName(name)
    }

    override suspend fun getAllTents(): List<TentEntity> {
        return appDatabase.getTentDao().getAllTents()
    }

    override suspend fun getTentWithBoxes(): List<TentWithBoxes> {
        return appDatabase.getTentDao().getTentWithBoxes()
    }
}