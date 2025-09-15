package org.smartroots.presentation.viewmodel

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import coil3.Bitmap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.core.logger.MESSAGE
import org.smartroots.data.database.entity.NoteEntity
import org.smartroots.data.repository.dbRepository.NoteRepository
import org.smartroots.platformImageToBytes
import org.smartroots.presentation.state.NoteState
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@ExperimentalTime
class NotesViewModel(val noteRepository: NoteRepository) : ViewModel() {
    private val _noteState = MutableStateFlow(NoteState())
    val noteState: StateFlow<NoteState> = _noteState.asStateFlow()
    val description = TextFieldState()
    val title = TextFieldState()

    fun updateImage(image: Bitmap) {
        _noteState.update { currentState ->
            currentState.copy(image = image)
        }
    }

    fun updateTitleErrorMessage(message: String) {
        _noteState.update { currentState ->
            currentState.copy(titleError = message, isLoading = false)
        }
    }

    fun updateDescriptionErrorMessage(message: String) {
        _noteState.update { currentState ->
            currentState.copy(descriptionError = message, isLoading = false)
        }
    }
    fun updateErrorMessage(message: String){
        _noteState.update { currentState ->
            currentState.copy(errorMessage = message, isLoading = false)
        }
    }

    fun validateState() {
        if (description.text.length < 10) {
            updateDescriptionErrorMessage("Please enter a longer description")
        }
        if (title.text.length < 5) {
            updateTitleErrorMessage("Please enter a longer title name")
        }

    }

    fun loading() {
        _noteState.update { currentState ->
            currentState.copy(isLoading = true)
        }
    }
    fun resetErrors(){
        _noteState.update { currentState ->
            currentState.copy(errorMessage = "", titleError = "", descriptionError = "")
        }
    }
   suspend fun onSave(): Long {
       resetErrors()
       loading()
       validateState()
       try {

           val currentMoment: Instant = Clock.System.now()
          // val datetimeInUtc: LocalDateTime = currentMoment.toLocalDateTime(TimeZone.UTC)
           val datetimeInSystemZone: LocalDateTime =
               currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())
           datetimeInSystemZone.toString()
           val bytes: ByteArray? = _noteState.value.image?.let { platformImageToBytes(it) }
           val noteEntity = NoteEntity(
               title = title.text.toString(),
               description = description.text.toString(),
               image = bytes,
               createdDate = datetimeInSystemZone.toString(),
           )
           val long = noteRepository.upsert(noteEntity)
           if(long>=0){
               return long
           }
       }catch (e: Exception){
          updateErrorMessage("Problem saving the note, please try again")
       }
       return -1
    }


}