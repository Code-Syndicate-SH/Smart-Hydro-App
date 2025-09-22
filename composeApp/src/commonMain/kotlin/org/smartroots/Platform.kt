package org.smartroots

import io.ktor.client.HttpClient

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
expect fun createHttpClient(): HttpClient
expect fun platformImageToBytes(image: Any): ByteArray
expect fun bytesToPlatformImage(bytes: ByteArray): Any