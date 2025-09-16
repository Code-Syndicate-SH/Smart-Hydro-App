package org.smartroots.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kashif.cameraK.controller.CameraController
import com.kashif.cameraK.enums.FlashMode

@Composable
fun CameraScreen(cameraController: CameraController){
cameraController.setFlashMode(FlashMode.ON)
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
         Row() {  Card(modifier = Modifier.width(200.dp).height(100.dp)) { Text("ell") } }}


}