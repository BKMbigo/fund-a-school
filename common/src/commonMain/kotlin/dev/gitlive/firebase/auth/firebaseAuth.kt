package dev.gitlive.firebase

import dev.gitlive.firebase.ActionCodeSettings
import dev.gitlive.firebase.AuthResult
import dev.gitlive.firebase.FirebaseUser

expect class FirebaseAuth {
    val currentUser: FirebaseUser?
    //    val authStateChanged: Flow<FirebaseUser?>
//    val idTokenChanged: Flow<FirebaseUser?>
//    var languageCode: String
//    suspend fun applyActionCode(code: String)
//    suspend fun <T: ActionCodeResult> checkActionCode(code: String): T
    suspend fun confirmPasswordReset(code: String, newPassword: String)
    suspend fun createUserWithEmailAndPassword(email: String, password: String): AuthResult
    //    suspend fun fetchSignInMethodsForEmail(email: String): List<String>
    suspend fun sendPasswordResetEmail(email: String, actionCodeSettings: ActionCodeSettings? = null)
    //    suspend fun sendSignInLinkToEmail(email: String, actionCodeSettings: ActionCodeSettings)
//    fun isSignInWithEmailLink(link: String): Boolean
    suspend fun signInWithEmailAndPassword(email: String, password: String): AuthResult
    //    suspend fun signInWithCustomToken(token: String): AuthResult
//    suspend fun signInAnonymously(): AuthResult
//    suspend fun signInWithCredential(authCredential: AuthCredential): AuthResult
//    suspend fun signInWithEmailLink(email: String, link: String): AuthResult
    suspend fun signOut()
    suspend fun updateCurrentUser(user: FirebaseUser)
    suspend fun verifyPasswordResetCode(code: String): String
//    fun useEmulator(host: String, port: Int)
}