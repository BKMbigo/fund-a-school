package dev.gitlive.firebase

actual class FirebaseApp(val android: com.google.firebase.FirebaseApp) {
    actual val name: String
        get() = android.name
//    actual val options: FirebaseOptions
//        get() = android.options.run { FirebaseOptions(applicationId, apiKey, databaseUrl = databaseUrl, messagingSenderId = gaTrackingId ?: "", storageBucket =  storageBucket ?: "", projectId =  projectId?: "") }

//    actual suspend fun delete() = android.delete()
}