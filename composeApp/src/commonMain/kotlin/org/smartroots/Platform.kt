package org.smartroots

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform