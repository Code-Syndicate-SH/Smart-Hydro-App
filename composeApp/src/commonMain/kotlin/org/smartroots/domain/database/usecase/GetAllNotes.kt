package org.smartroots.domain.database.usecase

import org.smartroots.data.database.entity.NoteEntity
import org.smartroots.data.repository.dbRepository.NoteRepository

class GetAllNotes(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(): List<NoteEntity> {
        return try {
            noteRepository.getAllNotes()
        } catch (e: Exception) {
            throw Exception(e) // make a custom message for this, or implement a custom exception
        }
    }
}