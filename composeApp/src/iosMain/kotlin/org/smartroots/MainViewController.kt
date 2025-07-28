package org.smartroots

import androidx.compose.ui.window.ComposeUIViewController
import kotlinx.coroutines.GlobalScope

fun MainViewController() = ComposeUIViewController {
    if (GlobalScope.getOrNull() == null) {
        initKoin{
            platformModule()
        }
    }
    App() }