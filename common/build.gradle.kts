import java.net.URI

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.googleServices)
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
                implementation(project(":firebase"))

                api(libs.kotlinx.coroutines)
                api(libs.kotlinx.datetime)
                api(libs.kotlinx.serialization.json)

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.ui)
                implementation(compose.animation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)

                implementation(libs.image.loader)

                implementation(libs.koin.core)
                implementation(libs.koin.compose)
            }
        }
        val commonTest by getting {}
        val androidMain by getting {

            dependencies {
                implementation(platform("androidx.compose:compose-bom:2023.05.01"))

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

                api(libs.square.card)

                implementation(libs.voyager.navigator)
                implementation(libs.voyager.transitions)
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)

                //implementation(libs.gitlive.auth)

                implementation(libs.routing.compose)


                implementation(npm("firebase", "9.4.1"))
            }
        }
    }
}

android {

    compileSdkVersion(33)
    namespace = "com.github.bkmbigo.fundaschool"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
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