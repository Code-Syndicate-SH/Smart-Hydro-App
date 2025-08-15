package org.smartroots.presentation

import kotlinx.serialization.Serializable

sealed class NavigationRoutes {
    @Serializable
    object SplashScreen

    @Serializable
    object Loading

    @Serializable
    object HomeScreen

    @Serializable
    object  ComponentInfo

    @Serializable
    object Notes
}