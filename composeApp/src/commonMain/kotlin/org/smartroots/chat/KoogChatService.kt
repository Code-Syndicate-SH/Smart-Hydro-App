package org.smartroots.chat

import ai.koog.agents.core.agent.AIAgent
import ai.koog.agents.core.tools.ToolRegistry


import ai.koog.agents.ext.tool.SayToUser



import ai.koog.prompt.executor.llms.all.simpleOllamaAIExecutor
import ai.koog.prompt.executor.llms.all.simpleOpenRouterExecutor
import ai.koog.prompt.executor.llms.all.simpleOpenAIExecutor


import ai.koog.prompt.llm.LLModel
import ai.koog.prompt.llm.LLMProvider
import ai.koog.prompt.llm.LLMCapability


import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

interface ChatService {
    suspend fun ask(userMessage: String): String
}

class KoogChatService(
    private val cfg: ChatConfig  // your existing config (Provider enum + keys)
) : ChatService {

    private val history = ArrayDeque<Pair<String, String>>() // (user, assistant)
    private val lock = Mutex()

    override suspend fun ask(userMessage: String): String {
        // 1) Pick executor + modelId string
        val (executor, modelId) = when (cfg.provider) {
            Provider.OLLAMA -> {
                val id = cfg.modelId ?: "llama3.1:8b"    // local + free
                simpleOllamaAIExecutor() to id
            }
            Provider.OPENROUTER -> {
                val key = cfg.apiKey ?: return "⚠️ Please set OPENROUTER_API_KEY."
                val id = cfg.modelId ?: "deepseek/deepseek-chat:free" // adjust as their free pool rotates
                simpleOpenRouterExecutor(key) to id
            }
            Provider.OPENAI -> {
                val key = cfg.apiKey ?: return "⚠️ Please set OPENAI_API_KEY."
                val id = cfg.modelId ?: "gpt-4o-mini"
                simpleOpenAIExecutor(key) to id
            }
        }

        // 2) Build the FINAL Koog model (constructor wants provider + caps + context length)
        val model = LLModel(
            id = modelId,
            provider = providerEnum(cfg.provider),
            // List<LLMCapability> (not Set). Pick the enum name that exists in your build.
            capabilities = listOf(LLMCapability.Completion), // or .Chat / .TEXT_CHAT
            contextLength = 8192
        )


        // 3) Small rolling context (single-arg lambda to avoid index overload)
        val compact = lock.withLock {
            history.takeLast(6).joinToString("\n") { p ->
                "User: ${p.first}\nAssistant: ${p.second}"
            }
        }
        val input = if (compact.isBlank()) userMessage else "$compact\nUser: $userMessage"

        // 4) Run the agent
        val agent = AIAgent(
            executor = executor,
            llmModel = model,
            systemPrompt = SMART_ROOTS_SYSTEM_PROMPT,
            temperature = cfg.temperature,
            toolRegistry = ToolRegistry { tool(SayToUser) },
            maxIterations = cfg.maxIterations
        )

        // Keep result handling generic across Koog minor variations
        val result = agent.run(input)
        val reply = result.toString().ifBlank { "Sorry, no response." }

        // 5) Save short history
        lock.withLock {
            if (history.size > 10) repeat(history.size - 10) { history.removeFirst() }
            history.addLast(userMessage to reply)
        }
        return reply
    }

    // Map your Provider -> Koog's LLMProvider
    private fun providerEnum(p: Provider): LLMProvider = when (p) {
        // Try the CamelCase names first (many 0.4.x builds use these)
        Provider.OLLAMA     -> LLMProvider.Ollama
        Provider.OPENROUTER -> LLMProvider.OpenRouter
        Provider.OPENAI     -> LLMProvider.OpenAI
    }

}
