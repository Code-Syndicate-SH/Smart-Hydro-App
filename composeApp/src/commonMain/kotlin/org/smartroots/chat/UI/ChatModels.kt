package org.smartroots.chat.ui

enum class Role { User, Fred }

data class ChatMessage(
    val role: Role,
    val text: String
)
