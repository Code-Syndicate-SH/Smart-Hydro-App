package org.smartroots

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.context.startKoin
import org.smartroots.presentation.screens.HomeScreen
import org.smartroots.presentation.screens.ScreenRoutes
import org.smartroots.presentation.viewmodel.HomeViewModel
import smartroots.composeapp.generated.resources.Res
import smartroots.composeapp.generated.resources.ic_soil_ph
import smartroots.composeapp.generated.resources.ic_temperature
import smartroots.composeapp.generated.resources.ic_water
import smartroots.composeapp.generated.resources.ic_lights
import smartroots.composeapp.generated.resources.ic_humidity
import smartroots.composeapp.generated.resources.ic_ec
import smartroots.composeapp.generated.resources.ic_notes
import smartroots.composeapp.generated.resources.ic_camera


@Composable
fun App(){
val homeViewModel = koinViewModel<HomeViewModel>()
    val navController = rememberNavController()

    Scaffold { innerPadding->
        Surface(modifier = Modifier.padding(innerPadding)) {
            NavHost(navController, startDestination = ScreenRoutes.HomeScreen) {
                composable<ScreenRoutes.HomeScreen> { HomeScreen(homeViewModel) }
            }
        }
    }
}