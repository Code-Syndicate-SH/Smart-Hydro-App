package org.smartroots.domain.database.usecase

import org.smartroots.data.database.Dao.NoteDao
import org.smartroots.data.database.entity.NoteEntity

class GetAllNotes(private val noteDao: NoteDao) {
    suspend operator fun invoke(): List<NoteEntity> {
        return try {
            noteDao.getAllNotes()
        } catch (e: Exception) {
            throw Exception(e) // make a custom message for this, or implement a custom exception
        }
    }
}