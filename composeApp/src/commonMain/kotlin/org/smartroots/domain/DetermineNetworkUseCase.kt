package org.smartroots.domain

class DetermineNetworkUseCase {
    operator fun invoke(ipv4:String):String{
        return when {

            ipv4.startsWith("192.168.8.") -> "Local"
            ipv4.startsWith("192.168.1.") -> "Remote"
            else -> "Unknown"
        }
    }
}