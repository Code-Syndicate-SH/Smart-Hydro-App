package org.smartroots.domain.database.usecase

import org.smartroots.data.database.Dao.BoxDao
import org.smartroots.data.database.entity.BoxEntity

class GetAllBoxes(private val boxDao: BoxDao) {

    suspend operator fun invoke():List<BoxEntity>{
     return try{
            boxDao.getAllBoxes()
        }
     catch (e: Exception){
          throw Exception(e) // check for a custom message for this
     }
    }
}