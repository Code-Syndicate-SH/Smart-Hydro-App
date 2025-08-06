package org.smartroots.data.database.Dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import org.smartroots.data.database.entity.NoteEntity

@Dao
interface NoteDao {
    @Upsert
    suspend fun upsert(note: NoteEntity): Long

    @Query("SELECT * FROM note WHERE note.noteId= :id")
    suspend fun getNoteWithId(id: Int): NoteEntity

    @Query("SELECT * FROM note")
    suspend fun getAllNotes(): List<NoteEntity>
}