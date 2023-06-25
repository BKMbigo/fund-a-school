package dev.gitlive.firebase

import kotlinx.coroutines.tasks.await

actual class FirebaseAuth internal constructor(val android: com.google.firebase.auth.FirebaseAuth) {
    actual val currentUser: FirebaseUser?
        get() = android.currentUser?.let { FirebaseUser(it) }

//    actual val authStateChanged: Flow<FirebaseUser?> get() = callbackFlow {
//        val listener = object : AuthStateListener {
//            override fun onAuthStateChanged(auth: com.google.firebase.auth.FirebaseAuth) {
//                trySend(auth.currentUser?.let { FirebaseUser(it) })
//            }
//        }
//        android.addAuthStateListener(listener)
//        awaitClose { android.removeAuthStateListener(listener) }
//    }

    //    actual val idTokenChanged get(): Flow<FirebaseUser?> = callbackFlow {
//        val listener = object : com.google.firebase.auth.FirebaseAuth.IdTokenListener {
//            override fun onIdTokenChanged(auth: com.google.firebase.auth.FirebaseAuth) {
//                trySend(auth.currentUser?.let { FirebaseUser(it) })
//            }
//        }
//        android.addIdTokenListener(listener)
//        awaitClose { android.removeIdTokenListener(listener) }
//    }
//
//    actual var languageCode: String
//        get() = android.languageCode ?: ""
//        set(value) { android.setLanguageCode(value) }
//
//
//    actual suspend fun applyActionCode(code: String) = android.applyActionCode(code).await().run { Unit }
    actual suspend fun confirmPasswordReset(code: String, newPassword: String) = android.confirmPasswordReset(code, newPassword).await().run { Unit }

    actual suspend fun createUserWithEmailAndPassword(email: String, password: String) =
        AuthResult(android.createUserWithEmailAndPassword(email, password).await())

//    actual suspend fun fetchSignInMethodsForEmail(email: String): List<String> = android.fetchSignInMethodsForEmail(email).await().signInMethods.orEmpty()

    actual suspend fun sendPasswordResetEmail(email: String, actionCodeSettings: ActionCodeSettings?) {
        android.sendPasswordResetEmail(email, actionCodeSettings?.toAndroid()).await()
    }

//    actual suspend fun sendSignInLinkToEmail(email: String, actionCodeSettings: ActionCodeSettings) = android.sendSignInLinkToEmail(email, actionCodeSettings.toAndroid()).await().run { Unit }
//
//    actual fun isSignInWithEmailLink(link: String) = android.isSignInWithEmailLink(link)

    actual suspend fun signInWithEmailAndPassword(email: String, password: String) =
        AuthResult(android.signInWithEmailAndPassword(email, password).await())

    //    actual suspend fun signInWithCustomToken(token: String) =
//        AuthResult(android.signInWithCustomToken(token).await())
//
//    actual suspend fun signInAnonymously() = AuthResult(android.signInAnonymously().await())
//
    actual suspend fun signInWithCredential(authCredential: AuthCredential) =
        AuthResult(android.signInWithCredential(authCredential.android).await())
//
//    actual suspend fun signInWithEmailLink(email: String, link: String) =
//        AuthResult(android.signInWithEmailLink(email, link).await())
//
    actual suspend fun signOut() = android.signOut()

    actual suspend fun updateCurrentUser(user: FirebaseUser) = android.updateCurrentUser(user.android).await().run { Unit }
    actual suspend fun verifyPasswordResetCode(code: String): String = android.verifyPasswordResetCode(code).await()

//    actual suspend fun <T : ActionCodeResult> checkActionCode(code: String): T {
//        val result = android.checkActionCode(code).await()
//        @Suppress("UNCHECKED_CAST")
//        return when(result.operation) {
//            SIGN_IN_WITH_EMAIL_LINK -> ActionCodeResult.SignInWithEmailLink
//            VERIFY_EMAIL -> ActionCodeResult.VerifyEmail(result.info!!.email)
//            PASSWORD_RESET -> ActionCodeResult.PasswordReset(result.info!!.email)
//            RECOVER_EMAIL -> (result.info as ActionCodeEmailInfo).run {
//                ActionCodeResult.RecoverEmail(email, previousEmail)
//            }
//            VERIFY_BEFORE_CHANGE_EMAIL -> (result.info as ActionCodeEmailInfo).run {
//                ActionCodeResult.VerifyBeforeChangeEmail(email, previousEmail)
//            }
//            REVERT_SECOND_FACTOR_ADDITION -> (result.info as ActionCodeMultiFactorInfo).run {
//                ActionCodeResult.RevertSecondFactorAddition(email, MultiFactorInfo(multiFactorInfo))
//            }
//            ERROR -> throw UnsupportedOperationException(result.operation.toString())
//            else -> throw UnsupportedOperationException(result.operation.toString())
//        } as T
//    }
//
//    actual fun useEmulator(host: String, port: Int) = android.useEmulator(host, port)
}