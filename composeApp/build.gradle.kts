
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget


plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    // plugins
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.ktorfit)
    alias(libs.plugins.ksp)

    /*   id("org.jetbrains.kotlin.plugin.serialization") version "2.2.0"
       id("de.jensklingenberg.ktorfit") version "2.5.2"
       id("com.google.devtools.ksp") version "2.2.0-2.0.2"*/

}
configurations.all {
    // drop any ktor-client-core-jvm before it ever goes into an Android dex jar
    exclude(group = "io.ktor", module = "ktor-client-core-jvm")
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
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.android)

            /*implementation("io.ktor:ktor-client-android:$ktorVersion")
              implementation(libs.androidx.)
             implementation("androidx.multidex:multidex:$multidexVersion")*/
        }

        nativeMain.dependencies {
            /*    implementation("io.ktor:ktor-client-darwin:$ktorVersion")*/
            implementation(libs.ktor.client.darwin)
        }

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(libs.jetbrains.compose.navigation)

            // koin dependancies
            implementation(libs.koin.core)
            api(libs.koin.annotations)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose.viewmodel.navigation)

            implementation(libs.kotlinx.serialization.json)

            implementation(libs.ktor.client.core)
            implementation(libs.ktorfit.lib)
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)
            /*    // kotlin serialization
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
                implementation("io.insert-koin:koin-compose-viewmodel-navigation:$koinVersion") */
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
            implementation(libs.ktor.client.java)

            //    implementation("io.ktor:ktor-client-java:$ktorVersion")

        }
    }
   /* sourceSets.named("commonMain").configure {
        kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
    }*/
}
ksp {
    arg("KOIN_USE_COMPOSE_VIEWMODEL", "true")
    arg("KOIN_CONFIG_CHECK", "true")
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
        multiDexEnabled = true
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

dependencies {
    debugImplementation(compose.uiTooling)
    add("kspCommonMainMetadata", libs.koin.ksp.compiler)
    add("kspAndroid", libs.koin.ksp.compiler)
    add("kspIosX64", libs.koin.ksp.compiler)
    add("kspIosArm64", libs.koin.ksp.compiler)
    add("kspIosSimulatorArm64", libs.koin.ksp.compiler)
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
project.afterEvaluate {
    // This block of code is now "lazy". It will only run after Gradle has
    // finished parsing the whole file and all plugins have created their tasks.

    val kspTasks = tasks.withType<com.google.devtools.ksp.gradle.KspTask>()

    // Use findByName for safety. It returns null instead of crashing if not found.
    val kspCommonMain = tasks.findByName("kspCommonMainKotlinMetadata")

    if (kspCommonMain != null) {
        kspTasks.configureEach {
            // Ensure the task is not the common one itself.
            if (name != kspCommonMain.name) {
                // This creates the dependency we need.
                dependsOn(kspCommonMain)
            }
        }
    } else {
        logger.warn("Task 'kspCommonMainKotlinMetadata' was not found. KSP dependency configuration will be skipped.")
    }
}