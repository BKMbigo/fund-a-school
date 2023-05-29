plugins {
    id("com.android.application")
    kotlin("android")
}

group = "com.github.bkmbigo.fundaschool"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
}

dependencies {
    implementation(project(":common"))

    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.appcompat:appcompat:1.6.1")

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

    implementation(libs.voyager.navigator)
}

android {
    compileSdkVersion(33)
    defaultConfig {
        applicationId = "com.github.bkmbigo.fundasmart.android"
        minSdkVersion(24)
        targetSdkVersion(33)
        versionCode = 1
        versionName = "1.0-SNAPSHOT"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.toString()
    }
}