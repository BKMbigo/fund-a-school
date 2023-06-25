package dev.gitlive.firebase

import dev.gitlive.firebase.*
import dev.gitlive.firebase.rethrow
import dev.gitlive.firebase.toJson
import kotlinx.coroutines.await

actual class FirebaseAuth internal constructor(val js: external.firebase.auth.Auth) {

    actual val currentUser: FirebaseUser?
        get() = rethrow { js.currentUser?.let { FirebaseUser(it) } }

//    actual val authStateChanged get() = callbackFlow<FirebaseUser?> {
//        val unsubscribe = js.onAuthStateChanged {
//            trySend(it?.let { FirebaseUser(it) })
//        }
//        awaitClose { unsubscribe() }
//    }

//    actual val idTokenChanged get() = callbackFlow<FirebaseUser?> {
//        val unsubscribe = js.onIdTokenChanged {
//            trySend(it?.let { FirebaseUser(it) })
//        }
//        awaitClose { unsubscribe() }
//    }

//    actual var languageCode: String
//        get() = js.languageCode ?: ""
//        set(value) { js.languageCode = value }

    //    actual suspend fun applyActionCode(code: String) = rethrow { js.applyActionCode(code).await() }
    actual suspend fun confirmPasswordReset(code: String, newPassword: String) = rethrow { external.firebase.auth.confirmPasswordReset(js, code, newPassword).await() }

    actual suspend fun createUserWithEmailAndPassword(email: String, password: String) =
        rethrow { AuthResult(external.firebase.auth.createUserWithEmailAndPassword(js, email, password).await()) }

//    actual suspend fun fetchSignInMethodsForEmail(email: String): List<String> = rethrow { js.fetchSignInMethodsForEmail(email).await().asList() }

    actual suspend fun sendPasswordResetEmail(email: String, actionCodeSettings: ActionCodeSettings?) =
        rethrow { external.firebase.auth.sendPasswordResetEmail(js, email, actionCodeSettings?.toJson()).await() }

//    actual suspend fun sendSignInLinkToEmail(email: String, actionCodeSettings: ActionCodeSettings) =
//        rethrow { js.sendSignInLinkToEmail(email, actionCodeSettings.toJson()).await() }
//
//    actual fun isSignInWithEmailLink(link: String) = rethrow { js.isSignInWithEmailLink(link) }

    actual suspend fun signInWithEmailAndPassword(email: String, password: String) =
        rethrow {
            val response = AuthResult(external.firebase.auth.signInWithEmailAndPassword(js, email, password).await())
            console.log("Auth Response is $response")
            response
        }

//    actual suspend fun signInWithCustomToken(token: String) =
//        rethrow { AuthResult(js.signInWithCustomToken(token).await()) }

//    actual suspend fun signInAnonymously() =
//        rethrow { AuthResult(js.signInAnonymously().await()) }

    suspend fun signInWithPopup(provider: AuthProvider) =
        rethrow { external.firebase.auth.signInWithPopup(js, provider.js).await() }
    actual suspend fun signInWithCredential(authCredential: AuthCredential) =
        rethrow { AuthResult(external.firebase.auth.signInWithCredential(js, authCredential.js).await()) }
//
//    actual suspend fun signInWithEmailLink(email: String, link: String) =
//        rethrow { AuthResult(js.signInWithEmailLink(email, link).await()) }

    actual suspend fun signOut() = rethrow { js.signOut().await() }

    actual suspend fun updateCurrentUser(user: FirebaseUser) =
        rethrow { external.firebase.auth.updateCurrentUser(js, user.js).await() }

    actual suspend fun verifyPasswordResetCode(code: String): String =
        rethrow { external.firebase.auth.verifyPasswordResetCode(js, code).await() }

//    actual suspend fun <T : ActionCodeResult> checkActionCode(code: String): T = rethrow {
//        val result = js.checkActionCode(code).await()
//        @Suppress("UNCHECKED_CAST")
//        return when(result.operation) {
//            "EMAIL_SIGNIN" -> ActionCodeResult.SignInWithEmailLink
//            "VERIFY_EMAIL" -> ActionCodeResult.VerifyEmail(result.data.email!!)
//            "PASSWORD_RESET" -> ActionCodeResult.PasswordReset(result.data.email!!)
//            "RECOVER_EMAIL" -> ActionCodeResult.RecoverEmail(result.data.email!!, result.data.previousEmail!!)
//            "VERIFY_AND_CHANGE_EMAIL" -> ActionCodeResult.VerifyBeforeChangeEmail(
//                result.data.email!!,
//                result.data.previousEmail!!
//            )
//            "REVERT_SECOND_FACTOR_ADDITION" -> ActionCodeResult.RevertSecondFactorAddition(
//                result.data.email!!,
//                result.data.multiFactorInfo?.let { MultiFactorInfo(it) }
//            )
//            else -> throw UnsupportedOperationException(result.operation)
//        } as T
//    }

//    actual fun useEmulator(host: String, port: Int) = rethrow { js.useEmulator("http://$host:$port") }
}