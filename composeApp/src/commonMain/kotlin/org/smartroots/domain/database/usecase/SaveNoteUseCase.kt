package org.smartroots.domain.database.usecase

import org.smartroots.data.database.entity.NoteEntity
import org.smartroots.data.repository.dbRepository.NoteRepository

class SaveNoteUseCase(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(noteEntity: NoteEntity): NoteEntity? {
        if (noteEntity.title.isEmpty() || noteEntity.title.length <= 3) {
            return null
        }
        try {
            val id: Int = noteRepository.upsert(noteEntity).toInt()
            val addedNote: NoteEntity = noteRepository.getNoteWithId(id)
            return addedNote
        } catch (e: Exception) {
            throw Exception("Unable to save the note: $e")
        }
    }
}