@file:OptIn(ExperimentalForeignApi::class)

package org.smartroots

import androidx.room.RoomDatabase
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import org.smartroots.data.database.AppDatabase
import platform.UIKit.UIDevice
import kotlinx.cinterop.*
import platform.Foundation.*
import platform.UIKit.*
import platform.UIKit.UIImageJPEGRepresentation

class IOSPlatform : Platform {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun platformModule() = module {
    single<RoomDatabase.Builder<AppDatabase>> {
        getDatabaseBuilder()
    }
}

actual fun createHttpClient(): HttpClient {
    return HttpClient(Darwin) { // Use OkHttp engine
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
}

actual fun platformImageToBytes(image: Any): ByteArray {
    val uiImage = image as UIImage
    // Use modern API (iOS 11+) -> jpegDataWithCompressionQuality
    val nsData: NSData? = UIImageJPEGRepresentation(uiImage, 0.9)
    return nsData?.toByteArray() ?: ByteArray(0)
}

actual fun bytesToPlatformImage(bytes: ByteArray): Any {
    val nsData = bytes.toNSData()
    return UIImage(data = nsData)!!
}

// Helpers
fun NSData.toByteArray(): ByteArray {
    val length = this.length.toInt()
    val byteArray = ByteArray(length)
    memScoped {
        val buffer = allocArray<ByteVar>(length)
        this@toByteArray.getBytes(buffer, length.toULong())
        for (i in 0 until length) {
            byteArray[i] = buffer[i]
        }
    }
    return byteArray
}

@OptIn(ExperimentalForeignApi::class)
fun ByteArray.toNSData(): NSData {
    return this.usePinned { pinned ->
        NSData.create(
            bytes = pinned.addressOf(0).reinterpret(),
            length = this.size.toULong()
        )
    }
}