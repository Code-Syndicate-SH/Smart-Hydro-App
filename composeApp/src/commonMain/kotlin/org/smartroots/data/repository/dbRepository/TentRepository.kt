package org.smartroots.data.repository.dbRepository

import org.smartroots.data.database.entity.TentEntity
import org.smartroots.data.relationships.TentWithBoxes

interface TentRepository {
    suspend fun getTentWithName(name: String): TentEntity

    suspend fun getAllTents(): List<TentEntity>

    suspend fun getTentWithBoxes():List<TentWithBoxes>
}