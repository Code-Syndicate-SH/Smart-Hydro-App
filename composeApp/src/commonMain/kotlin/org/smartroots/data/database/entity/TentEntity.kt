package org.smartroots.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "tent")
data class TentEntity(
    @PrimaryKey (autoGenerate = true)
    val tentId:Int = 0, // add on to this
    val name:String,
    val location:String,
)
