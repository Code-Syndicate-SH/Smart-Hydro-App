package org.smartroots.data.repository.dbRepository

import org.smartroots.data.database.entity.NoteEntity

interface NoteRepository {
    suspend fun insert(note: NoteEntity): Long
    suspend fun getAllNotes(): List<NoteEntity>
}
