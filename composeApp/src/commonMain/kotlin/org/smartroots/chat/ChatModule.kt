// commonMain/kotlin/org/smartroots/chat/ChatModule.kt
package org.smartroots.chat

import org.koin.dsl.module

val chatModule = module {
    single {
        ChatConfig(
            provider = Provider.OLLAMA,   // free/local by default
            apiKey = null,                // not needed for Ollama
            modelId = null,               // defaults to "llama3.1:8b"
            temperature = 0.2,
            maxIterations = 8
        )
    }
    single<ChatService> { KoogChatService(get()) }
}
