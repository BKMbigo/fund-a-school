package dev.gitlive.firebase

actual class AuthResult internal constructor(val android: com.google.firebase.auth.AuthResult) {
    actual val user: FirebaseUser?
        get() = android.user?.let { FirebaseUser(it) }
}