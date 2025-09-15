package org.smartroots

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import org.koin.compose.koinInject
import org.smartroots.chat.ChatService
import org.smartroots.chat.ui.ChatViewModel
import org.smartroots.chat.ui.ChatScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        // Pull ChatService from Koin and create a lightweight VM
        val chatService: ChatService = koinInject()
        val vm = remember(chatService) { ChatViewModel(chatService) }

        ChatScreen(
            viewModel = vm,
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize()
        )
    }
}
