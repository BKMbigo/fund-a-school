/*
 * Copyright (c) 2020 GitLive Ltd.  Use of this source code is governed by the Apache 2.0 license.
 */

package dev.gitlive.firebase

import dev.gitlive.firebase.*
import kotlinx.coroutines.await
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlin.js.json

//actual val Firebase.auth
//    get() = rethrow { FirebaseAuth(external.firebase.auth.i) }

actual fun Firebase.auth(app: FirebaseApp) =
    rethrow { FirebaseAuth(external.firebase.auth.initializeAuth(app.js)) }

actual class AuthResult internal constructor(val js: external.firebase.auth.UserCredential) {
    actual val user: FirebaseUser?
        get() = rethrow { FirebaseUser(js.user) }
}

actual class AuthTokenResult(val js: external.firebase.auth.IdTokenResult) {
//    actual val authTimestamp: Long
//        get() = js.authTime
    actual val claims: Map<String, Any>
        get() = (js("Object").keys(js.claims) as Array<String>).mapNotNull {
                key -> js.claims[key]?.let { key to it }
        }.toMap()
//    actual val expirationTimestamp: Long
//        get() = js.expirationTime
//    actual val issuedAtTimestamp: Long
//        get() = js.issuedAtTime
    actual val signInProvider: String?
        get() = js.signInProvider
    actual val token: String?
        get() = js.token
}

internal fun ActionCodeSettings.toJson() = json(
    "url" to url,
    "android" to (androidPackageName?.run { json("installApp" to installIfNotAvailable, "minimumVersion" to minimumVersion, "packageName" to packageName) } ?: undefined),
    "dynamicLinkDomain" to (dynamicLinkDomain ?: undefined),
    "handleCodeInApp" to canHandleCodeInApp,
    "ios" to (iOSBundleId?.run { json("bundleId" to iOSBundleId) } ?: undefined)
)

actual open class FirebaseAuthException(code: String?, cause: Throwable): FirebaseException(code, cause)
actual open class FirebaseAuthActionCodeException(code: String?, cause: Throwable): FirebaseAuthException(code, cause)
actual open class FirebaseAuthEmailException(code: String?, cause: Throwable): FirebaseAuthException(code, cause)
actual open class FirebaseAuthInvalidCredentialsException(code: String?, cause: Throwable): FirebaseAuthException(code, cause)
actual open class FirebaseAuthInvalidUserException(code: String?, cause: Throwable): FirebaseAuthException(code, cause)
actual open class FirebaseAuthMultiFactorException(code: String?, cause: Throwable): FirebaseAuthException(code, cause)
actual open class FirebaseAuthRecentLoginRequiredException(code: String?, cause: Throwable): FirebaseAuthException(code, cause)
actual open class FirebaseAuthUserCollisionException(code: String?, cause: Throwable): FirebaseAuthException(code, cause)
actual open class FirebaseAuthWebException(code: String?, cause: Throwable): FirebaseAuthException(code, cause)


internal inline fun <T, R> T.rethrow(function: T.() -> R): R = dev.gitlive.firebase.rethrow { function() }

private inline fun <R> rethrow(function: () -> R): R {
    try {
        return function()
    } catch (e: Exception) {
        throw e
    } catch(e: dynamic) {
        throw errorToException(e)
    }
}

private fun errorToException(cause: dynamic) = when(val code = cause.code?.toString()?.lowercase()) {
    "auth/invalid-user-token" -> FirebaseAuthInvalidUserException(code, cause)
    "auth/requires-recent-login" -> FirebaseAuthRecentLoginRequiredException(code, cause)
    "auth/user-disabled" -> FirebaseAuthInvalidUserException(code, cause)
    "auth/user-not-found" -> FirebaseAuthInvalidUserException(code, cause)
    "auth/user-token-expired" -> FirebaseAuthInvalidUserException(code, cause)
    "auth/web-storage-unsupported" -> FirebaseAuthWebException(code, cause)
    "auth/network-request-failed" -> FirebaseNetworkException(code, cause)
    "auth/invalid-credential",
    "auth/wrong-password",
    "auth/invalid-email",
    "auth/invalid-verification-code",
    "auth/missing-verification-code",
    "auth/invalid-verification-id",
    "auth/missing-verification-id" -> FirebaseAuthInvalidCredentialsException(code, cause)
    "auth/maximum-second-factor-count-exceeded",
    "auth/second-factor-already-in-use" -> FirebaseAuthMultiFactorException(code, cause)
    "auth/credential-already-in-use" -> FirebaseAuthUserCollisionException(code, cause)
    "auth/email-already-in-use" -> FirebaseAuthUserCollisionException(code, cause)
    "auth/invalid-email" -> FirebaseAuthEmailException(code, cause)

//                "auth/app-deleted" ->
//                "auth/app-not-authorized" ->
//                "auth/argument-error" ->
//                "auth/invalid-api-key" ->
//                "auth/operation-not-allowed" ->
//                "auth/too-many-arguments" ->
//                "auth/unauthorized-domain" ->
    else -> {
        println("Unknown error code in ${JSON.stringify(cause)}")
        FirebaseAuthException(code, cause)
    }
}