@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.composeMultiplatform)
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

                implementation(libs.firebase.firestore)

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.ui)
                implementation(compose.animation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)

                implementation(libs.image.loader)
                implementation(libs.voyager.navigator)
                implementation(libs.voyager.transitions)
            }
        }
        val commonTest by getting {}
        val androidMain by getting {

            dependencies {
                implementation(platform("androidx.compose:compose-bom:2023.05.01"))

                implementation(libs.androidx.core.ktx)
                implementation(libs.androidx.activity)

                implementation(libs.android.argon.kt)

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
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
                implementation(npm("argon2-browser", "^1.0.0"))
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