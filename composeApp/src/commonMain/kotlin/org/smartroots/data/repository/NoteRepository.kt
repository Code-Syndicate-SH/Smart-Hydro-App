package org.smartroots.data.repository

import org.smartroots.data.database.entity.NoteEntity

interface NoteRepository {
    suspend fun insert(note: NoteEntity)
    suspend fun getAllNotes():List<NoteEntity>
}