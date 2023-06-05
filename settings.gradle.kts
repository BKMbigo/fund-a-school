import java.net.URI

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven {
            url = URI.create("https://sdk.squareup.com/public/android")
        }
    }
}

rootProject.name = "fund-a-school"

include(":android", ":common")
