package org.smartroots.data.dao

import androidx.room.Embedded
import androidx.room.Relation
import org.smartroots.data.entity.BoxEntity
import org.smartroots.data.entity.TentEntity

data class TentWithBoxes(
    @Embedded val tent: TentEntity,
    @Relation(
        parentColumn = "tentId",
        entityColumn = "tentId"
    )
    val boxes:List<BoxEntity>
)
