# Fund a school

This project is currently incomplete and currently not actively being developed/maintained. Please refer to the projects mentioned below for complete or actively maintained projects.


Had an idea to create a multiplatform(Android/Js) application to help in sourcing of funds for education in various parts of the world. 

## Getting Started
The project utilizes [Kotlin Multiplatform](https://kotlinlang.org/lp/multiplatform/) to enable sharing of code between different platforms (namely, Android and Web). Additionally, UI code is shared using Compose Multiplatform. If you are new to the tech stack, please go through the following projects as they are easier to learn and build:
* [Official Compose Multiplatform Samples](https://github.com/Kotlin/kotlin-wasm-examples/tree/main/compose-imageviewer#compose-multiplatform-for-web)
* [NYTimes-KMP](https://github.com/xxfast/NYTimes-KMP)
* [TravelApp-KMP](https://github.com/SEAbdulbasit/TravelApp-KMP)
* [MusicApp-KMP](https://github.com/SEAbdulbasit/MusicApp-KMP)


***

## Libraries Used
###### The project is developed using [Kotlin](https://kotlinlang.org/).

##### Common libraries
* Compose
  * [Jetpack Compose on Android](https://developer.android.com/jetpack/compose)
  * [Compose for web](https://github.com/JetBrains/compose-multiplatform)
* [Ktor Client](https://ktor.io/)
* [Compose ImageLoader](https://github.com/qdsfdhvh/compose-imageloader)
* [Koin](https://insert-koin.io/)
* [Multiplatform Settings](https://github.com/russhwolf/multiplatform-settings)

#### Android Libraries
* Firebase
  * Firebase Auth
  * Firebase Firestore
  * Firebase Crashlytics
* [Voyager](https://voyager.adriel.cafe/)

#### Javascript Libraries
* Firebase
    * Firebase Auth
    * Firebase Firestore
* [Routing compose](https://github.com/hfhbd/routing-compose)

###### For firebase, I based the project on [firebase-kotlin-sdk]() to support but had to tweak some parts of the library that either could not compile or the dependencies could not be identified by the compiler. I will share more details in the issues.

## Project Structure
The project contains two modules: 
  * [Android]() - used to build the android application.
  * [Common]() - contains the shared and platform specific code. SourceSets Include: 
      * [commonMain]() 
      * [androidMain]()
      * [jsMain]()

###### Dependencies and their versions are contained in the [libs.versions.toml]()
## Project Layering
The project is split to the following layers: 
  * [data]() - Contains business login. Defines how data is created, stored, updated and deleted in the application.
    * [firebase]() - Contains specific logic concerning connecting and utilizing firebase in the application.
    * [preferences]() - Contains specific logic on storage of user preferences in the application.
  * [di]() - Handles dependency injection.
  * [domain]() - Contains abstractions that separate concerns between ui and other layers.
  * [js]()(Web Only) - Contains external definitions for javascript libraries.
  * [presentation]() - Handles displaying of elements on the screen and handling of user actions.

## Screenshots


## Contribution
