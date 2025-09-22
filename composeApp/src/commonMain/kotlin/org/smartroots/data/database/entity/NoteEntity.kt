package org.smartroots.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName ="note" )
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val noteId:Int = 0,
    val createdDate: String?,
    val title:String,
    val description:String,
    val image: ByteArray?,
)

