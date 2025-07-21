package org.smartroots.data.relationships

import androidx.room.Embedded
import androidx.room.Relation
import org.smartroots.data.database.entity.BoxEntity
import org.smartroots.data.database.entity.SensorActivityReading

data class BoxWithSensors (
    @Embedded val  box: BoxEntity,
    @Relation(
        parentColumn = "boxId",
       entityColumn = "boxId"
    )
    val sensors:List<SensorActivityReading>
)
