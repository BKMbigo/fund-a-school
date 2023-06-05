/*
 * Copyright (c) 2020 GitLive Ltd.  Use of this source code is governed by the Apache 2.0 license.
 */

@file:Suppress("EXTENSION_SHADOWED_BY_MEMBER")

package dev.gitlive.firebase

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseApp
import dev.gitlive.firebase.FirebaseException
import kotlinx.coroutines.flow.Flow

//expect val Firebase.auth: FirebaseAuth

expect fun Firebase.auth(app: FirebaseApp): FirebaseAuth

expect class AuthResult {
    val user: FirebaseUser?
}

//expect class AuthTokenResult {
//    val authTimestamp: Long
//    val claims: Map<String, Any>
//    val expirationTimestamp: Long
//    val issuedAtTimestamp: Long
//    val signInProvider: String?
//    val token: String?
//}

//sealed class ActionCodeResult {
//    object SignInWithEmailLink : ActionCodeResult()
//    class PasswordReset internal constructor(val email: String) : ActionCodeResult()
//    class VerifyEmail internal constructor(val email: String) : ActionCodeResult()
//    class RecoverEmail internal constructor(val email: String, val previousEmail: String) : ActionCodeResult()
//    class VerifyBeforeChangeEmail internal constructor(val email: String, val previousEmail: String) : ActionCodeResult()
//    class RevertSecondFactorAddition internal constructor(val email: String, val multiFactorInfo: MultiFactorInfo?) : ActionCodeResult()
//}

data class ActionCodeSettings(
    val url: String,
    val androidPackageName: AndroidPackageName? = null,
    val dynamicLinkDomain: String? = null,
    val canHandleCodeInApp: Boolean = false,
    val iOSBundleId: String? = null
)

data class AndroidPackageName(
    val packageName: String,
    val installIfNotAvailable: Boolean = true,
    val minimumVersion: String? = null
)

expect open class FirebaseAuthException : FirebaseException
expect class FirebaseAuthActionCodeException : FirebaseAuthException
expect class FirebaseAuthEmailException : FirebaseAuthException
expect class FirebaseAuthInvalidCredentialsException : FirebaseAuthException
expect class FirebaseAuthInvalidUserException : FirebaseAuthException
expect class FirebaseAuthMultiFactorException: FirebaseAuthException
expect class FirebaseAuthRecentLoginRequiredException : FirebaseAuthException
expect class FirebaseAuthUserCollisionException : FirebaseAuthException
expect class FirebaseAuthWebException : FirebaseAuthException
