import org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile
import java.net.URI

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.ksp)
}

repositories {
    google()
    mavenCentral()
    maven {
        url = URI.create("https://sdk.squareup.com/public/android")
    }
}

kotlin {
    android()
    js(IR) {
        browser()
        binaries.executable()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {

                api(libs.kotlinx.coroutines)
                api(libs.kotlinx.datetime)
                api(libs.kotlinx.serialization.json)

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.ui)
                implementation(compose.animation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                implementation(libs.image.loader)

                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)

                implementation(libs.koin.core)
                implementation(libs.koin.compose)

                implementation(libs.multiplatform.settings)
                implementation(libs.multiplatform.settings.no.arg)
            }
        }
        val commonTest by getting {}
        val androidMain by getting {

            dependencies {
                implementation(platform("androidx.compose:compose-bom:2023.05.01"))

                api(libs.kotlinx.coroutines.jdk8)

                implementation(libs.androidx.core.ktx)
                implementation(libs.androidx.activity)

                implementation(libs.compose.runtime)
                implementation(libs.compose.foundation)
                implementation(libs.compose.foundation.layout)
                implementation(libs.compose.material.icons)
                implementation(libs.compose.material3)
                implementation(libs.compose.ui)
                implementation(libs.compose.ui.util)
                implementation(libs.compose.ui.tooling.preview)
                implementation(libs.compose.ui.graphics)
                implementation(libs.compose.animation)

                implementation(libs.ktor.client.android)

                implementation(libs.koin.android)
                implementation(libs.koin.androidx.compose)

                implementation(libs.voyager.navigator)
                implementation(libs.voyager.transitions)

                implementation(platform("com.google.firebase:firebase-bom:32.1.0"))
                implementation(libs.firebase.auth.ktx)
                implementation(libs.firebase.firestore.ktx)
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)

                implementation(libs.routing.compose)
                implementation(libs.ktor.client.js)

                implementation(npm("firebase", "9.4.1"))
            }
        }
    }
}

android {

    compileSdkVersion(33)
    namespace = "com.github.bkmbigo.fundaschool"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(33)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

compose {
    experimental {
        web.application {}
    }
    kotlinCompilerPlugin.set(dependencies.compiler.forKotlin("1.8.20"))
    kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=1.8.21")
}


// In response to Issue: https://github.com/JetBrains/compose-multiplatform/issues/2701
//tasks.withType<KotlinJsCompile>().configureEach {
//    kotlinOptions.freeCompilerArgs += listOf(
//        "-Xklib-enable-signature-clash-checks=false",
//    )
//}