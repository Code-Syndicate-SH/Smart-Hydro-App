package org.smartroots.data.repository.dbRepository

import org.smartroots.data.database.Dao.NoteDao
import org.smartroots.data.database.entity.NoteEntity

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {
    override suspend fun insert(note: NoteEntity): Long {
        return noteDao.insert(note)
    }

    override suspend fun getAllNotes(): List<NoteEntity> {
        return noteDao.getAllNotes()
    }

}