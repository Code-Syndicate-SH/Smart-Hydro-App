package org.smartroots.data.repository.dbRepository

import org.smartroots.data.database.AppDatabase
import org.smartroots.data.database.entity.NoteEntity

class NoteLocalDbRepository(private val appDatabase: AppDatabase): NoteRepository {
    override suspend fun insert(note: NoteEntity) {
        return appDatabase.getNoteDao().insert(note)
    }

    override suspend fun getAllNotes(): List<NoteEntity> {
        return appDatabase.getNoteDao().getAllNotes()
    }

}