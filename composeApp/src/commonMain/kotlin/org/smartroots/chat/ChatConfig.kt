package org.smartroots.chat

/** Prioritise free providers: OLLAMA (local) and OPENROUTER (often-free pool). */
enum class Provider { OLLAMA, OPENROUTER, OPENAI }

data class ChatConfig(
    val provider: Provider = Provider.OLLAMA,
    /** Not needed for OLLAMA */
    val apiKey: String? = null,
    /** Provider-specific model id; if null we’ll pick sane defaults per provider */
    val modelId: String? = null,
    val temperature: Double = 0.2,
    val maxIterations: Int = 8
)
