@file:JsModule("firebase/auth")
@file:JsNonModule

package external.firebase.auth

import external.firebase.app.FirebaseApp
import kotlin.js.Json
import kotlin.js.Promise

external fun getAuth(app: FirebaseApp): Auth
external fun getAuth(): Auth

external fun initializeAuth(app: FirebaseApp): Auth
external fun initializeAuth(app: FirebaseApp, deps: Dependencies): Auth

external fun applyActionCode(auth: Auth, oobCode: String): Promise<Unit>
//external fun beforeAuthStateChanged(
//    auth: Auth,
//    callback: (User?) -> Unit,
//    onAbort: () -> Unit
//): Unsubscribe

external fun checkActionCode(auth: Auth, oobCode: String): Promise<ActionCodeInfo>
external fun confirmPasswordReset(auth: Auth, oobCode: String, newPassword: String): Promise<Unit>
external fun connectAuthEmulator(auth: Auth, url: String)
external fun createUserWithEmailAndPassword(
    auth: Auth,
    email: String,
    password: String
): Promise<UserCredential>

external fun fetchSignInMethodsForEmail(auth: Auth, email: String): Promise<List<String>>
external fun getMultiFactorResolver(auth: Auth, error: MultiFactorError): MultiFactorResolver
external fun getRedirectResult(auth: Auth): Promise<UserCredential?>
external fun getRedirectResult(
    auth: Auth,
    resolver: PopupRedirectResolver
): Promise<UserCredential?>

external fun initializeRecaptchaConfig(auth: Auth): Promise<Unit>
external fun isSignInWithEmailLink(auth: Auth, emailLink: String): Boolean

external fun sendPasswordResetEmail(auth: Auth, email: String): Promise<Unit>

//external fun onAuthStateChanged(auth: Auth, nextOrObserver: NextOrObserver<User>, error: ErrorFn?, completed: CompleteFn?): Unsubscribe
//external fun onIdTokenChanged(auth: Auth, nextOrObserver: NextOrObserver<User>, error?: ErrorFn, completed?: CompleteFn): Unsubscribe;
external fun sendPasswordResetEmail(
    auth: Auth,
    email: String,
    actionCodeSettings: ActionCodeSettings
): Promise<Unit>

external fun sendPasswordResetEmail(
    auth: Auth,
    email: String,
    actionCodeSettings: Any?
): Promise<Unit>

external fun sendSignInLinkToEmail(
    auth: Auth,
    email: String,
    actionCodeSettings: ActionCodeSettings
): Promise<Unit>

external fun sendSignInLinkToEmail(
    auth: Auth,
    email: String,
    actionCodeSettings: Any
): Promise<Unit>

external fun setPersistence(auth: Auth, persistence: Persistence): Promise<Unit>
external fun signInAnonymously(auth: Auth): Promise<UserCredential>
external fun signInWithCredential(auth: Auth, credential: AuthCredential): Promise<UserCredential>
external fun signInWithCustomToken(auth: Auth, customToken: String): Promise<UserCredential>
external fun signInWithEmailAndPassword(
    auth: Auth,
    email: String,
    password: String
): Promise<UserCredential>

external fun signInWithEmailLink(auth: Auth, email: String): Promise<UserCredential>
external fun signInWithEmailLink(
    auth: Auth,
    email: String,
    emailLink: String
): Promise<UserCredential>

external fun signInWithPhoneNumber(
    auth: Auth,
    phoneNumber: String,
    appVerifier: ApplicationVerifier
): Promise<ConfirmationResult>

external fun signInWithPopup(auth: Auth, provider: AuthProvider): Promise<UserCredential>
external fun signInWithPopup(
    auth: Auth,
    provider: AuthProvider,
    resolver: PopupRedirectResolver
): Promise<UserCredential>

external fun signInWithRedirect(auth: Auth, provider: AuthProvider): Promise<Unit>
external fun signInWithRedirect(
    auth: Auth,
    provider: AuthProvider,
    resolver: PopupRedirectResolver
): Promise<Unit>

external fun signOut(auth: Auth): Promise<Unit>
external fun updateCurrentUser(auth: Auth, user: User?): Promise<Unit>
external fun useDeviceLanguage(auth: Auth)
external fun parseActionCodeURL(link: String): ActionCodeURL?
external fun deleteUser(user: User): Promise<Unit>
external fun getIdToken(user: User): Promise<String>
external fun getIdToken(user: User, forceRefresh: Boolean): Promise<String>
external fun getIdTokenResult(user: User): Promise<IdTokenResult>
external fun getIdTokenResult(user: User, forceRefresh: Boolean): Promise<IdTokenResult>
external fun linkWithCredential(user: User, credential: AuthCredential): Promise<UserCredential>
external fun linkWithPhoneNumber(
    user: User,
    phoneNumber: String,
    appVerifier: ApplicationVerifier
): Promise<ConfirmationResult>

external fun linkWithPopup(user: User, provider: AuthProvider): Promise<UserCredential>
external fun linkWithPopup(
    user: User,
    provider: AuthProvider,
    resolver: PopupRedirectResolver?
): Promise<UserCredential>

external fun linkWithRedirect(user: User, provider: AuthProvider): Promise<Unit>
external fun linkWithRedirect(
    user: User,
    provider: AuthProvider,
    resolver: PopupRedirectResolver?
): Promise<Unit>

external fun multiFactor(user: User): MultiFactorUser
external fun reauthenticateWithCredential(
    user: User,
    credential: AuthCredential
): Promise<UserCredential>

external fun reauthenticateWithPhoneNumber(
    user: User,
    phoneNumber: String,
    appVerifier: ApplicationVerifier
): Promise<ConfirmationResult>

external fun reauthenticateWithPopup(user: User, provider: AuthProvider): Promise<UserCredential>
external fun reauthenticateWithPopup(
    user: User,
    provider: AuthProvider,
    resolver: PopupRedirectResolver
): Promise<UserCredential>

external fun reauthenticateWithRedirect(user: User, provider: AuthProvider): Promise<Unit>
external fun reauthenticateWithRedirect(
    user: User,
    provider: AuthProvider,
    resolver: PopupRedirectResolver
): Promise<Unit>

external fun reload(user: User): Promise<Unit>
external fun sendEmailVerification(user: User): Promise<Unit>
external fun sendEmailVerification(
    user: User,
    actionCodeSettings: ActionCodeSettings?
): Promise<Unit>

external fun unlink(user: User, providerId: String): Promise<User>
external fun updateEmail(user: User, newEmail: String): Promise<Unit>
external fun updatePassword(user: User, newPassword: String): Promise<Unit>
external fun updatePhoneNumber(user: User, credential: PhoneAuthCredential): Promise<Unit>

//export declare function updateProfile(user: User, { displayName, photoURL: photoUrl }: {
//    displayName?: string | null;
//    photoURL?: string | null;
//}): Promise<void>;
external fun verifyBeforeUpdateEmail(user: User, newEmail: String): Promise<Unit>
external fun verifyBeforeUpdateEmail(
    user: User,
    newEmail: String,
    actionCodeSettings: ActionCodeSettings?
): Promise<Unit>

external fun getAdditionalUserInfo(userCredential: UserCredential): AdditionalUserInfo?
external fun verifyPasswordResetCode(auth: Auth, code: String): Promise<String>

external enum class ActionCodeOperation {
    EMAIL_SIGNIN,
    PASSWORD_RESET,
    RECOVER_EMAIL,
    REVERT_SECOND_FACTOR_ADDITION,
    VERIFY_AND_CHANGE_EMAIL,
    VERIFY_EMAIL
}

external val browserLocalPersistence: Persistence
external val browserPopupRedirectResolver: PopupRedirectResolver
external val browserSessionPersistence: Persistence
external val cordovaPopupRedirectResolver: PopupRedirectResolver

external enum class FactorId {
    PHONE,
    TOTP
}

external val inMemoryPersistence: Persistence
external val indexedDBLocalPersistence: Persistence

external enum class OperationType {
    LINK,
    REAUTHENTICATE,
    SIGN_IN
}

external enum class ProviderId {
    FACEBOOK,
    GITHUB,
    GOOGLE,
    PASSWORD,
    PHONE,
    TWITTER
}

external interface PhoneInfoOptions

external interface ActionCodeInfo {
    val data: ActionCodeData
//    val operation: ActionCodeOperation
}

external class ActionCodeData {
    val email: String?
    val multiFactorInfo: MultiFactorInfo?
    val previousEmail: String?
}

external interface ActionCodeSettings {
    val android: AndroidActionCodeSettings?
    val dynamicLinkDomain: String?
    val handleCodeInApp: Boolean?
    val iOS: iOSActionCodeSettings?
    val url: String
}

external interface AndroidActionCodeSettings {
    val installApp: Boolean?
    val minimumVersion: String?
    val packageName: String
}

external interface iOSActionCodeSettings {
    val bundleId: String?
}

external class ActionCodeURL
external interface AdditionalUserInfo {
    val isNewUser: Boolean
    val profile: Map<String, Any>?
    val providerId: String?
    val username: String?
}

external interface ApplicationVerifier {
    val type: String
    fun verify(): Promise<String>
}

external interface Auth {
    val app: FirebaseApp

    //    val config: Config
    val currentUser: User?

    //    val enumalatorConfig: EmulatorConfig?
    val languageCode: String?
    val name: String
    val settings: AuthSettings
    val tenantId: String?

    //    fun beforeAuthStateChanged(callback: (user: User?) -> Unit): Unsubscribe
    //    fun beforeAuthStateChanged(callback: (user: User?) -> Promise<Unit>): Unsubscribe
    //    fun beforeAuthStateChanged(callback: (user: User?) -> Unit, onAbort: () -> Unit): Unsubscribe
    //    fun beforeAuthStateChanged(
    //        callback: (user: User?) -> Promise<Unit>,
    //        onAbort: () -> Unit
    //    ): Unsubscribe

    //    fun onAuthStateChanged(nextOrObserver: NextOrObserver<User | null>, error?: ErrorFn, completed?: CompleteFn): Unsubscribe;
    //    fun onIdTokenChanged(nextOrObserver: NextOrObserver<User | null>, error?: ErrorFn, completed?: CompleteFn): Unsubscribe;
    fun setPersistence(persistence: Persistence): Promise<Unit>
    fun signOut(): Promise<Unit>
    fun updateCurrentUser(user: User?): Promise<Unit>
    fun useDeviceLanguage()
}

open external class AuthCredential {
    val providerId: String
    val signInMethod: String
    open fun toJSON(): Any
}

external interface AuthProvider {
    //val providerId: String
}

external interface AuthSettings {
    val appVerificationDisabledForTesting: Boolean
}

external interface ConfirmationResult {
    val verificationId: String
    fun confirm(verificationCode: String): Promise<UserCredential>
}

external interface Dependencies {
    val persistence: Persistence
    val popupRedirectResolver: PopupRedirectResolver?
}

external class EmailAuthCredential : AuthCredential {
    fun fromJSON(json: Any): EmailAuthCredential?
    fun fromJSON(json: String): EmailAuthCredential?
    fun toJson(): Any
}


external class EmailAuthProvider : AuthProvider {
    val providerId: String = definedExternally

    companion object {
        val EMAIL_LINK_SIGN_IN_METHOD: String = definedExternally
        val EMAIL_PASSWORD_SIGN_IN_METHOD: String = definedExternally
        val PROVIDER_ID: String = definedExternally

        fun credential(email: String, password: String): EmailAuthCredential
        fun credentialWithLink(email: String, emailLink: String): EmailAuthCredential
    }
}

external interface EmulatorConfig {
    val host: String

    //    val options:
    val port: Int?
    val protocol: String
}

abstract external class BaseOAuthProvider: AuthProvider

external class FacebookAuthProvider : BaseOAuthProvider {
    companion object {
        val FACEBOOK_SIGN_IN_METHOD: String = definedExternally
        val PROVIDER_ID: String = definedExternally
        fun credential(accessToken: String): OAuthCredential

        //        fun credentialFromError(error: FirebaseError): OAuthCredential?
        fun credentialFromResult(userCredential: UserCredential): OAuthCredential?
    }
}

external class GithubAuthProvider : BaseOAuthProvider {
    companion object {
        val GITHUB_SIGN_IN_METHOD: String = definedExternally
        val PROVIDER_ID: String = definedExternally
        fun credential(accessToken: String): OAuthCredential

        //        fun credentialFromError(error: FirebaseError): OAuthCredential?
        fun credentialFromResult(userCredential: UserCredential): OAuthCredential?
    }
}

external class GoogleAuthProvider : BaseOAuthProvider {
    companion object {
        val GOOGLE_SIGN_IN_METHOD: String = definedExternally
        val PROVIDER_ID: String = definedExternally
        fun credential(idToken: String?, accessToken: String?): OAuthCredential

        //        fun credentialFromError(error: FirebaseError): OAuthCredential?
        fun credentialFromResult(userCredential: UserCredential): OAuthCredential?
    }
}

external interface IdTokenResult {
    val authTime: String
    val claims: Json
    val expirationTime: String
    val issuedAtTime: String
    val signInProvider: String?
    val signInSecondFactor: String?
    val token: String
}

external interface MultiFactorAssertion {
    val factorId: FactorId
}

external interface MultiFactorError {
//    val customData: Any
}

external interface MultiFactorInfo {
    val displayName: String?
    val enrollmentTime: String
    val factorId: FactorId
    val uid: String
}

external interface MultiFactorResolver {
    val hints: List<MultiFactorInfo>
    val session: MultiFactorSession

    fun resolveSignIn(assertion: MultiFactorAssertion): Promise<UserCredential>
}

external interface MultiFactorSession
external interface MultiFactorUser {
    val enrolledFactors: List<MultiFactorInfo>
    fun enroll(assertion: MultiFactorAssertion, displayName: String?): Promise<Unit>
    fun getSession(): Promise<MultiFactorSession>
    fun unenroll(option: MultiFactorInfo): Promise<Unit>
    fun unenroll(option: String): Promise<Unit>
}

external class OAuthCredential : AuthCredential {
    val accessToken: String
    val idToken: String
    val secret: String

    override fun toJSON(): Any

    companion object {
        fun fromJSON(json: Any): OAuthCredential?
        fun fromJSON(json: String): OAuthCredential?
    }
}

external interface OAuthCredentialOptions {
    val accessToken: String?
    val idToken: String?
    val rawNonce: String?
}

external class OAuthProvider(providerId: String): BaseOAuthProvider {
    fun credential(params: OAuthCredentialOptions): OAuthCredential

    fun credential(optionsOrIdToken: Any?, accessToken: String?): AuthCredential
    fun addScope(scope: String)
    fun setCustomParameters(customOAuthParameters: Map<String, String>)

    companion object {
        //        fun credentialFromError(error: FirebaseError): OAuthCredential?
        fun credentialFromJSON(json: Any): OAuthCredential
        fun credentialFromJSON(json: String): OAuthCredential
        fun credentialFromResult(userCredential: UserCredential): OAuthCredential?
    }
}

external interface ParsedToken {
    val auth_time: String
    val exp: String

    //    val firebase: Any
    val iat: String
    val sub: String
}

external interface Persistence {
    val type: String
}

external class PhoneAuthCredential : AuthCredential {
    override fun toJSON(): Any

    companion object {
        fun fromJSON(json: Any): PhoneAuthCredential?
        fun fromJSON(json: String): PhoneAuthCredential?
    }
}

external class PhoneAuthProvider(auth: Auth): AuthProvider {

    val providerId: String = definedExternally
    fun verifyPhoneNumber(
        phoneOptions: PhoneInfoOptions,
        applicationVerifier: ApplicationVerifier
    ): Promise<String>

    fun verifyPhoneNumber(
        phoneOptions: String,
        applicationVerifier: ApplicationVerifier
    ): Promise<String>

    companion object {
        val PHONE_SIGN_IN_METHOD: String = definedExternally
        val PROVIDER_ID: String = definedExternally
        fun credential(verificationId: String, verificationCode: String): PhoneAuthCredential

        //        fun credentialFromError(error: FirebaseError): AuthCredential?
        fun credentialFromResult(userCredential: UserCredential): AuthCredential?
    }
}

external interface PhoneMultiFactorAssertion : MultiFactorAssertion
external interface PhoneMultiFactorEnrollInfoOptions : PhoneInfoOptions {
    val type: String
    val session: MultiFactorSession
}

external class PhoneMultiFactorGenerator {
    companion object {
        val FACTOR_ID: String
        fun assertion(credential: PhoneAuthCredential): PhoneMultiFactorAssertion
    }
}

external interface PhoneMultiFactorInfo : MultiFactorInfo {
    val phoneNumber: String
}

external interface PhoneMultiFactorSignInInfoOptions : PhoneInfoOptions {
    val multiFactorHint: MultiFactorInfo?
    val multiFactorUid: String?
    val session: MultiFactorSession
}

external interface PhoneSingleFactorInfoOptions : PhoneInfoOptions {
    val phoneNumber: String
}

external interface PopupRedirectResolver
external interface RecaptchaParameters
external class RecaptchaVerifier: ApplicationVerifier {
    override val type: String = definedExternally
    fun clear()
    fun render(): Promise<Int>
    override fun verify(): Promise<String>
}
//external class SAMLAuthProvider: FederatedAuthProvider
external interface TotpMultiFactorAssertion: MultiFactorAssertion
external class TotpMultiFactorGenerator {
    companion object {
        val FACTOR_ID: String = definedExternally
        fun assertionForEnrollment(secret: TotpSecret, oneTimePassword: String): TotpMultiFactorAssertion
        fun assertionForSignIn(enrollmentId: String, oneTimePassword: String): TotpMultiFactorAssertion
        fun generateSecret(session: MultiFactorSession): Promise<TotpSecret>
    }
}
external interface TotpMultiFactorInfo: MultiFactorInfo
external class TotpSecret {
    val codeIntervalSeconds: Int
    val codeLength: Int
    val enrollmentCompletionDeadline: String
    val hashingAlgorithm: String
    val secretKey: String
    fun generateQrCodeUrl(accountName: String?, issuer: String?): String
}
external class TwitterAuthProvider(): BaseOAuthProvider {
    companion object {
        val PROVIDER_ID: String = definedExternally
        val TWITTER_SIGN_IN_METHOD: String = definedExternally
        fun credential(token: String, secret: String): OAuthCredential
//        fun credentialFromError(error: FirebaseError): OAuthCredential?
        fun credentialFromResult(userCredential: UserCredential): OAuthCredential?;
    }
}



external interface User : UserInfo {
    val emailVerified: Boolean
    val isAnonymous: Boolean
    val metadata: UserMetadata
    val providerData: List<UserInfo>
    val refreshToken: String
    val tenantId: String?

    fun delete(): Promise<Unit>
    fun getIdToken(): Promise<String>
    fun getIdToken(forceRefresh: Boolean): Promise<String>
    fun getIdTokenResult(forceRefresh: Boolean?): Promise<IdTokenResult>
    fun reload(): Promise<Unit>
    fun toJSON(): Any
}
external interface UserCredential {
    val operationType: OperationType
    val providerId: String?
    val user: User
}
external interface UserInfo {
    val displayName: String?
    val email: String?
    val phoneNumber: String
    val photoURL: String
    val providerId: String
    val uid: String
}
external interface UserMetadata {
    val creationTime: String
    val lastSignInTime: String
}