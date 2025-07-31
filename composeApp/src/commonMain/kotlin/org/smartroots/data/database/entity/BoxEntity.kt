package org.smartroots.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "box")
data class BoxEntity(
    @PrimaryKey (autoGenerate = true)
    val boxId:Int = 0,
    val category:String,
    val tentId:Int        // we just need to revivew how to do relationships in this system.
)
