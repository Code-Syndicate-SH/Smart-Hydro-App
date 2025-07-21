package org.smartroots.data.relationships

import androidx.room.Embedded
import androidx.room.Relation
import org.smartroots.data.database.entity.BoxEntity
import org.smartroots.data.database.entity.TentEntity

data class TentWithBoxes(
    @Embedded val tent: TentEntity,
    @Relation(
        parentColumn = "tentId",
        entityColumn = "tentId"
    )
    val boxes:List<BoxEntity>
)
