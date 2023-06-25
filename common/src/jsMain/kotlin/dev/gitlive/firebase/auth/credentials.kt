package dev.gitlive.firebase

import external.firebase.auth.UserCredential
import kotlinx.coroutines.await
import kotlin.js.json

abstract class AuthProvider(open val js: external.firebase.auth.AuthProvider)

actual open class AuthCredential(val js: external.firebase.auth.AuthCredential) {
    actual val providerId: String
        get() = js.providerId
}

actual class PhoneAuthCredential(js: external.firebase.auth.AuthCredential) : AuthCredential(js)
actual class OAuthCredential(js: external.firebase.auth.AuthCredential) : AuthCredential(js)

actual object EmailAuthProvider: AuthProvider(external.firebase.auth.EmailAuthProvider()) {
    actual fun credential(email: String, password: String): AuthCredential =
        AuthCredential(external.firebase.auth.EmailAuthProvider.credential(email, password))

    actual fun credentialWithLink(
        email: String,
        emailLink: String
    ): AuthCredential = AuthCredential(external.firebase.auth.EmailAuthProvider.credentialWithLink(email, emailLink))
}

actual object FacebookAuthProvider: AuthProvider(external.firebase.auth.FacebookAuthProvider()) {
    actual fun credential(accessToken: String): AuthCredential =
        AuthCredential(external.firebase.auth.FacebookAuthProvider.credential(accessToken))
}

actual object GithubAuthProvider: AuthProvider(external.firebase.auth.GithubAuthProvider()) {
    actual fun credential(token: String): AuthCredential =
        AuthCredential(external.firebase.auth.GithubAuthProvider.credential(token))
}

actual object GoogleAuthProvider: AuthProvider(external.firebase.auth.GoogleAuthProvider()) {
    actual fun credential(idToken: String?, accessToken: String?): AuthCredential {
        require(idToken != null || accessToken != null) {
            "Both parameters are optional but at least one must be present."
        }
        return AuthCredential(external.firebase.auth.GoogleAuthProvider.credential(idToken, accessToken))
    }

    fun credentialFromResult(userCredential: UserCredential): AuthCredential? =
        external.firebase.auth.GoogleAuthProvider.credentialFromResult(userCredential)?.let { AuthCredential(it) }
}

actual class OAuthProvider(override val js: external.firebase.auth.OAuthProvider): AuthProvider(js) {

    actual constructor(
        provider: String,
        scopes: List<String>,
        customParameters: Map<String, String>,
        auth: FirebaseAuth
    ) : this(external.firebase.auth.OAuthProvider(provider))  {
        rethrow {
            scopes.forEach { js.addScope(it) }
            js.setCustomParameters(customParameters)
        }
    }
    actual companion object {
        actual fun credential(providerId: String, accessToken: String?, idToken: String?, rawNonce: String?): OAuthCredential = rethrow {
            external.firebase.auth.OAuthProvider(providerId)
                .credential(
                    json(
                        "accessToken" to (accessToken ?: undefined),
                        "idToken" to (idToken ?: undefined),
                        "rawNonce" to (rawNonce ?: undefined)
                    ),
                    accessToken ?: undefined
                )
                .let { OAuthCredential(it) }
        }
    }
}

actual class PhoneAuthProvider(override val js: external.firebase.auth.PhoneAuthProvider): AuthProvider(js) {

    actual constructor(auth: FirebaseAuth) : this(external.firebase.auth.PhoneAuthProvider(auth.js))

    actual fun credential(verificationId: String, smsCode: String): PhoneAuthCredential = PhoneAuthCredential(external.firebase.auth.PhoneAuthProvider.credential(verificationId, smsCode))
    actual suspend fun verifyPhoneNumber(phoneNumber: String, verificationProvider: PhoneVerificationProvider): AuthCredential = rethrow {
        val verificationId = js.verifyPhoneNumber(phoneNumber, verificationProvider.verifier).await()
        val verificationCode = verificationProvider.getVerificationCode(verificationId)
        credential(verificationId, verificationCode)
    }
}

actual interface PhoneVerificationProvider {
    val verifier: external.firebase.auth.ApplicationVerifier
    suspend fun getVerificationCode(verificationId: String): String
}

actual object TwitterAuthProvider {
    actual fun credential(token: String, secret: String): AuthCredential = AuthCredential(external.firebase.auth.TwitterAuthProvider.credential(token, secret))
}
