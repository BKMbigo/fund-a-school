@file:JsModule("firebase/app")
@file:JsNonModule

package external.firebase.app

import kotlin.js.Promise


external fun deleteApp(app: FirebaseApp): Promise<Unit>
external fun getApps(): Array<FirebaseApp>
external fun initializeApp(): FirebaseApp
external fun registerVersion(libraryKeyOrName: String, version: String, variant: String)
// Skipped Some Functions
external fun getApp(name: String): FirebaseApp
external fun initializeApp(options: Any): FirebaseApp
//external fun initializeApp(options: Any, config: Any): FirebaseApp

external interface FirebaseApp {
    val name: String
    val options: FirebaseOptions
}

external interface FirebaseAppSettings {
    val name: String
}

external interface FirebaseOptions {
    val apiKey: String
    val appId: String
    val authDomain: String
    val databaseURL: String
    val measurementId: String
    val messagingSenderId: String
    val projectId: String
    val storageBucket: String
}