package org.smartroots.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class TentEntity(
    @PrimaryKey (autoGenerate = true)
    val tentId:Int, // add on to this
    val name:String,
    val location:String,
)
