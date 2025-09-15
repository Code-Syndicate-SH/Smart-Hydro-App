package org.smartroots.presentation.state

import coil3.Bitmap

data class NoteState(
    val createdDate: String? = "",
    val image: Bitmap? = null,
    val showCamera: Boolean = false,
    val hasCameraPermission:Boolean = false,
    val isLoading: Boolean = false,
    val success:Boolean  = true,
    val descriptionError:String="",
    val titleError:String = "",
    val errorMessage:String = ""
)
