package org.smartroots.data.repository

import org.smartroots.data.database.entity.TentEntity

interface TentRepository {
    suspend fun getTentWithName(name: String): TentEntity

    suspend fun getAllTents(): List<TentEntity>
}