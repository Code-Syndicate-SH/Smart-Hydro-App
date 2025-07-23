package org.smartroots.domain.database.usecase

import org.smartroots.data.database.entity.NoteEntity
import org.smartroots.data.repository.dbRepository.NoteRepository

class SaveNoteUseCase(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(): NoteEntity {
        TODO()
    }
}