package org.smartroots


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.room.RoomDatabase

import dev.tmapps.konnection.Konnection
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

import org.koin.android.ext.koin.androidContext

import okio.ByteString.Companion.toByteString

import org.koin.dsl.module
import org.smartroots.data.database.AppDatabase
import java.io.ByteArrayOutputStream

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun platformModule() = module {
    single<RoomDatabase.Builder<AppDatabase>> {
        getDatabaseBuilder(get())
    }

    single { createHttpClient() }

}


actual fun createHttpClient(): HttpClient {
    return HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }

    }
}

actual fun platformImageToBytes(image: Any): ByteArray {
    val bitmap = image as Bitmap
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream)
    return stream.toByteArray()
}

actual fun bytesToPlatformImage(bytes: ByteArray): Any {
    return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
}