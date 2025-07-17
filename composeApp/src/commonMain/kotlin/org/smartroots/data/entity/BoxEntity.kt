package org.smartroots.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class BoxEntity(
    @PrimaryKey (autoGenerate = true)
    val boxId:Int,
    val category:String,
    val tentId:Int        // we just need to revivew how to do relationships in this system.
)
