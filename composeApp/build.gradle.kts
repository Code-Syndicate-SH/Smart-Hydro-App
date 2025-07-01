import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    id("org.jetbrains.kotlin.plugin.serialization") version "2.2.0"
    id("de.jensklingenberg.ktorfit") version "2.5.2"
    id("com.google.devtools.ksp") version "2.2.0-2.0.2"
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        val ktorfitVersion = "2.5.1"
        val ktorVersion = "3.2.0"
        val koinVersion = "4.1.0"
        val koinAnnotaionsVersion = "2.1.0"
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation("io.ktor:ktor-client-android:$ktorVersion")
        }

        nativeMain.dependencies {
            implementation("io.ktor:ktor-client-darwin:$ktorVersion")
        }
//
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            // kotlin serialization
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")

            // coroutines for async
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")

            // ktorfit( for using a retrofit like system), api requests
            implementation("de.jensklingenberg.ktorfit:ktorfit-lib:$ktorfitVersion")

            // below is for coil3 image loading
            implementation("io.coil-kt.coil3:coil-compose:3.2.0")
            implementation("io.coil-kt.coil3:coil-network-ktor3:$ktorVersion")

            // dependency injection
            implementation("io.insert-koin:koin-core:$koinVersion")
            api("io.insert-koin:koin-annotations:$koinAnnotaionsVersion")
            //life cycle
            implementation("io.insert-koin:koin-compose:$koinVersion")
            implementation("io.insert-koin:koin-compose-viewmodel:$koinVersion")
            implementation("io.insert-koin:koin-compose-viewmodel-navigation:$koinVersion")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
            implementation("io.ktor:ktor-client-java:$ktorVersion")
        }
    }
}

android {
    namespace = "org.smartroots"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.smartroots"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "DebugProbesKt.bin"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
ksp {
    arg("KOIN_USE_COMPOSE_VIEWMODEL","true")
}
dependencies {
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "org.smartroots.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.smartroots"
            packageVersion = "1.0.0"
        }
    }
}
