package org.smartroots.data.repository.dbRepository

import org.smartroots.data.database.Dao.NoteDao
import org.smartroots.data.database.entity.NoteEntity

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {
    override suspend fun upsert(note: NoteEntity): Long {
        return noteDao.upsert(note)
    }

    override suspend fun getAllNotes(): List<NoteEntity> {
        return noteDao.getAllNotes()
    }

    override suspend fun getNoteWithId(id: Int): NoteEntity {
        return noteDao.getNoteWithId(id)
    }

}