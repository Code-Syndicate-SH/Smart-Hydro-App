package org.smartroots.presentation.screens

import kotlinx.serialization.Serializable

sealed class  ScreenRoutes {
    @Serializable
    object HomeScreen

    @Serializable
    object SplashScreen

    @Serializable
    object LoadingScreen

    @Serializable
    object SensorControlScreen

    @Serializable
    object NotesScreen
    @Serializable
    object AddNotesScreen

    @Serializable
    object CameraPreviewScreen
    @Serializable
    object CameraScreen
}