package org.smartroots.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.kashif.cameraK.controller.CameraController
import com.kashif.cameraK.enums.CameraLens
import com.kashif.cameraK.enums.Directory
import com.kashif.cameraK.enums.FlashMode
import com.kashif.cameraK.enums.ImageFormat
import com.kashif.cameraK.ui.CameraPreview

@Composable
fun CameraPreviewScreen(){
    val cameraController = remember { mutableStateOf<CameraController?>(null) }
    CameraPreview (
        modifier = Modifier.fillMaxSize(),
        cameraConfiguration = {
            setCameraLens(CameraLens.BACK)
            setFlashMode(FlashMode.OFF)
            setImageFormat(ImageFormat.JPEG)
           setDirectory(Directory.PICTURES)
           // addPlugin(imageSaverPlugin)
         //   addPlugin(qrScannerPlugin)
        },
        onCameraControllerReady = {
            cameraController.value = it
         //   qrScannerPlugin.startScanning()
        }
    )
    cameraController.value?.let { controller ->
        CameraScreen(cameraController = controller, )

    }
}