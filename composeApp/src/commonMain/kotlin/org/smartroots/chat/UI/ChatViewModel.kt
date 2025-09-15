package org.smartroots.chat.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.smartroots.chat.ChatService

class ChatViewModel(
    private val chatService: ChatService
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages = _messages.asStateFlow()

    fun send(text: String) {
        if (text.isBlank()) return

        // add user message immediately
        val pre = _messages.value + ChatMessage(Role.User, text)
        _messages.value = pre

        scope.launch {
            val reply = chatService.ask(text)
            _messages.value = _messages.value + ChatMessage(Role.Fred, reply)
        }
    }
}
