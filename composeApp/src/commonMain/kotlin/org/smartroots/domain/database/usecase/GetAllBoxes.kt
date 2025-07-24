package org.smartroots.domain.database.usecase

import org.smartroots.data.database.entity.BoxEntity
import org.smartroots.data.repository.dbRepository.BoxRepository

class GetAllBoxes(private val boxRepository: BoxRepository) {

    suspend operator fun invoke(): List<BoxEntity> {
        return try {
            boxRepository.getAllBoxes()
        } catch (e: Exception) {
            throw Exception(e) // check for a custom message for this
        }
    }
}