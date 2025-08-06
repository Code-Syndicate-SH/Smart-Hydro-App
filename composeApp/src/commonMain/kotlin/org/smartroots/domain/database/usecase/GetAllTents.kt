package org.smartroots.domain.database.usecase

import org.smartroots.data.database.entity.TentEntity
import org.smartroots.data.repository.dbRepository.TentRepository

class GetAllTents(
    private val tentRepository: TentRepository,
) {
    suspend operator fun invoke(): List<TentEntity> {

        return try {
            tentRepository.getAllTents()
        } catch (e: Exception) {
            throw Exception("Something went wrong!, \n $e")
        }
    }
}

