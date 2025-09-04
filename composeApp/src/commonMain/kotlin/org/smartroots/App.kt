package org.smartroots

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.compose.viewmodel.koinViewModel
import org.smartroots.presentation.screens.HomeScreen
import org.smartroots.presentation.screens.ScreenRoutes
import org.smartroots.presentation.viewmodel.HomeViewModel


@Composable
fun App() {
    val homeViewModel = koinViewModel<HomeViewModel>()
    val navController = rememberNavController()

    Scaffold { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            NavHost(navController, startDestination = ScreenRoutes.HomeScreen) {
                composable<ScreenRoutes.HomeScreen> { HomeScreen(homeViewModel) }
            }
        }
    }
}