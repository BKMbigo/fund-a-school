@file:JsModule("firebase/auth")
@file:JsNonModule

package external.firebase.auth

import external.firebase.app.FirebaseApp
import kotlin.js.Promise

external fun getAuth(app: FirebaseApp): Auth
external fun getAuth(): Auth

external fun initializeAuth(app: FirebaseApp): Auth

external fun confirmPasswordReset(auth: Auth, oobCode: String, newPassword: String): Promise<Unit>

external fun createUserWithEmailAndPassword(auth: Auth, email: String, password: String): Promise<UserCredential>

external fun sendPasswordResetEmail(auth: Auth, email: String, actionCodeSettings: Any?): Promise<Unit>
external fun signInWithEmailAndPassword(auth: Auth, email: String, password: String): Promise<UserCredential>
external fun signOut(auth: Auth): Promise<Unit>
external fun updateCurrentUser(auth: Auth, user: User?): Promise<Unit>
external fun verifyPasswordResetCode(auth: Auth, code: String): Promise<String>

external interface Auth {
    val app: external.firebase.app.FirebaseApp
    val currentUser: User?

    fun signOut(): Promise<Unit>
    fun updateCurrentUser(): Promise<Unit>
}

external interface User: UserInfo {
    val emailVerified: Boolean
}

external interface UserCredential{
    val user: User
}
external interface UserInfo {
    val displayName: String?
    val email: String?
    val phoneNumber: String
    val photoURL: String
    val uid: String
}