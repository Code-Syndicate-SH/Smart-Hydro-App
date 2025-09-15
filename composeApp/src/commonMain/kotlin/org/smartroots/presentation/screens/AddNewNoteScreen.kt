package org.smartroots.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.rememberAsyncImagePainter
import com.kashif.cameraK.permissions.providePermissions
import org.jetbrains.compose.resources.painterResource
import org.smartroots.presentation.viewmodel.NotesViewModel
import smartroots.composeapp.generated.resources.Res
import smartroots.composeapp.generated.resources.logo
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun AddNewNoteScreen(notesViewModel: NotesViewModel) {
    val uiState by notesViewModel.noteState.collectAsStateWithLifecycle()
    val permissions = providePermissions()

    val cameraPermissionState = remember { mutableStateOf(permissions.hasCameraPermission()) }
// Request permissions if needed
    if (!cameraPermissionState.value) {
        permissions.RequestCameraPermission(
            onGranted = { cameraPermissionState.value = true },
            onDenied = { notesViewModel.updateErrorMessage("Camera Permission Denied") }
        )
        notesViewModel.updateCameraPermission(cameraPermissionState.value)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF121212))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color(0xFF121212), RoundedCornerShape(10.dp))
        ) {
            if (uiState.image != null) {
                Image(
                    painter = rememberAsyncImagePainter("imageUri"),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Image(
                    painter = painterResource(Res.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff00AFEF),
                    contentColor = Color(0xFF121212)
                )
            ) {
                Text(
                    "Upload Image",
                    //  style = TextStyle(fontFamily = leagueSpartan)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    // this will take you to the preview
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFA8CF45),
                    contentColor = Color(0xFF121212)
                )
            ) {
                Text(
                    "Take Photo",
                    //     style = TextStyle(fontFamily = leagueSpartan)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            state = notesViewModel.title,

            label = { Text("Title") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(10.dp)),

            )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            state = notesViewModel.description,
            placeholder = { Text("Description") },

            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.White, RoundedCornerShape(10.dp)),

            )

        Spacer(modifier = Modifier.height(22.dp))

        Button(
            onClick = {

                notesViewModel.onSave()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF9D21),
                contentColor = Color(0xFF121212)
            )
        ) {
            Text(
                "Save Note",
                style = TextStyle(fontSize = 18.sp)
            )
        }
    }
}