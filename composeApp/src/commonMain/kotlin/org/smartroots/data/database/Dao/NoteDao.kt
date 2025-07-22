package org.smartroots.data.database.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.smartroots.data.database.entity.NoteEntity
@Dao
interface NoteDao {
    @Insert
    suspend fun insert(note: NoteEntity): Long

    @Query("SELECT * FROM note")
    suspend fun getAllNotes():List<NoteEntity>
}