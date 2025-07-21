package org.smartroots.data.repository

import org.smartroots.data.database.AppDatabase
import org.smartroots.data.database.entity.TentEntity

class LocalTentDbRepository(private val appDatabase: AppDatabase): TentRepository {
    override suspend fun getTentWithName(name: String): TentEntity {
        return appDatabase.getTentDao().getTentWithName(name)
    }

    override suspend fun getAllTents(): List<TentEntity> {
        return appDatabase.getTentDao().getAllTents()
    }
}