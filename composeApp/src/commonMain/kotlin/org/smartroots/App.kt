package org.smartroots

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import org.smartroots.presentation.screens.AddNewNoteScreen
import org.smartroots.presentation.screens.HomeScreen
import org.smartroots.presentation.screens.NoteScreen
import org.smartroots.presentation.screens.ScreenRoutes
import org.smartroots.presentation.viewmodel.HomeViewModel
import org.smartroots.presentation.viewmodel.NotesViewModel
import kotlin.time.ExperimentalTime


@OptIn(ExperimentalTime::class)
@Composable
fun App() {
    val homeViewModel = koinViewModel<HomeViewModel>()
    val notesViewModel = koinViewModel<NotesViewModel>()
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
           /* ExtendedFloatingActionButton(
               content = {},
                onClick = {
                    scope.launch {
                        val result = snackbarHostState
                            .showSnackbar(
                                message = "${homeViewModel.homeUIState.value.networkError}",
                                actionLabel = "Action",
                                // Defaults to SnackbarDuration.Short
                                duration = SnackbarDuration.Indefinite
                            )
                        when (result) {
                            SnackbarResult.ActionPerformed -> {
                                /* Handle snackbar action performed */
                            }
                            SnackbarResult.Dismissed -> {
                                /* Handle snackbar dismissed */
                            }
                        }
                    }
                }
            )*/
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            NavHost(navController, startDestination = ScreenRoutes.HomeScreen) {
                composable<ScreenRoutes.HomeScreen> { HomeScreen(homeViewModel, onNotesClick = {navController.navigate(
                    ScreenRoutes.NotesScreen)}) }
                composable <ScreenRoutes.NotesScreen>{ NoteScreen(onAddNote = {navController.navigate(
                    ScreenRoutes.AddNotesScreen)} )}
                composable<ScreenRoutes.AddNotesScreen> { AddNewNoteScreen(notesViewModel) }
            }
        }
    }
}