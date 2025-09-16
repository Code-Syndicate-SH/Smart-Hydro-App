package org.smartroots.presentation.screens

import androidx.compose.runtime.Composable
import com.kashif.cameraK.controller.CameraController
import com.kashif.cameraK.enums.FlashMode

@Composable
fun CameraScreen(cameraController: CameraController){
cameraController.setFlashMode(FlashMode.ON)
}