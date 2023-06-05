/*
 * Copyright (c) 2020 GitLive Ltd.  Use of this source code is governed by the Apache 2.0 license.
 */

@file:JsModule("firebase/compat/app")
@file:JsNonModule

package dev.gitlive.firebase.common

import kotlin.js.Json
import kotlin.js.Promise

//@JsName("default")
//external object firebase {

//    open class App {
//        val name: String
//        val options: Options
//        fun delete()
//        fun functions(region: String? = definedExternally): functions.Functions
//        fun database(url: String? = definedExternally): database.Database
//    }

//    interface Options {
//        val appId: String
//        val apiKey: String
//        val authDomain: String?
//        val databaseUrl: String?
//        val measurementId: String?
//        val projectId: String?
//        val storageBucket: String?
//        val messagingSenderId: String?
//
//    }

    //val apps : Array<App>
    //fun app(name: String? = definedExternally): App
    //fun initializeApp(options: Any, name: String? = definedExternally) : App

//    interface FirebaseError {
//        var code: String
//        var message: String
//        var name: String
//    }

//    fun auth(app: App? = definedExternally): external.firebase.auth.Auth
//
//    object auth {
//        open class Auth {
//            val currentUser: user.User?
//            var languageCode: String?
//
//            fun useEmulator(url: String)
//            fun applyActionCode(code: String): Promise<Unit>
//            fun checkActionCode(code: String): Promise<ActionCodeInfo>
//            fun confirmPasswordReset(code: String, newPassword: String): Promise<Unit>
//            fun createUserWithEmailAndPassword(email: String, password: String): Promise<AuthResult>
//            fun fetchSignInMethodsForEmail(email: String): Promise<Array<String>>
//            fun sendPasswordResetEmail(email: String, actionCodeSettings: Any?): Promise<Unit>
//            fun sendSignInLinkToEmail(email: String, actionCodeSettings: Any?): Promise<Unit>
//            fun isSignInWithEmailLink(link: String): Boolean
//            fun signInWithEmailAndPassword(email: String, password: String): Promise<AuthResult>
//            fun signInWithCustomToken(token: String): Promise<AuthResult>
//            fun signInAnonymously(): Promise<AuthResult>
//            fun signInWithCredential(authCredential: AuthCredential): Promise<AuthResult>
//            fun signInWithPopup(provider: AuthProvider): Promise<AuthResult>
//            fun signInWithRedirect(provider: AuthProvider): Promise<Unit>
//            fun signInWithEmailLink(email: String, link: String): Promise<AuthResult>
//            fun getRedirectResult(): Promise<AuthResult>
//            fun signOut(): Promise<Unit>
//            fun updateCurrentUser(user: user.User?): Promise<Unit>
//            fun verifyPasswordResetCode(code: String): Promise<String>
//
//            fun onAuthStateChanged(nextOrObserver: (user.User?) -> Unit): () -> Unit
//            fun onIdTokenChanged(nextOrObserver: (user.User?) -> Unit): () -> Unit
//        }
//
//        abstract class IdTokenResult {
//            val authTime: String
//            val claims: Json
//            val expirationTime: String
//            val issuedAtTime: String
//            val signInProvider: String?
//            val signInSecondFactor: String?
//            val token: String
//        }
//
//        abstract class AuthResult {
//            val credential: AuthCredential?
//            val operationType: String?
//            val user: user.User?
//        }
//
//        abstract class AuthCredential {
//            val providerId: String
//            val signInMethod: String
//        }
//
//        abstract class ActionCodeInfo {
//            val operation: String
//            val data: ActionCodeData
//        }
//
//        abstract class ActionCodeData {
//            val email: String?
//            val multiFactorInfo: multifactor.MultiFactorInfo?
//            val previousEmail: String?
//        }
//
//        interface ActionCodeSettings {
//            val android: AndroidActionCodeSettings?
//            val dynamicLinkDomain: String?
//            val handleCodeInApp: Boolean?
//            val iOS: iOSActionCodeSettings?
//            val url: String
//        }
//
//        interface AndroidActionCodeSettings {
//            val installApp: Boolean?
//            val minimumVersion: String?
//            val packageName: String
//        }
//
//        interface iOSActionCodeSettings {
//            val bundleId: String?
//        }
//
//        interface AuthProvider
//
//        class EmailAuthProvider : AuthProvider {
//            companion object {
//                fun credential(email :  String, password : String): AuthCredential
//                fun credentialWithLink(email: String, emailLink: String): AuthCredential
//            }
//        }
//
//        class FacebookAuthProvider : AuthProvider {
//            companion object {
//                fun credential(token: String): AuthCredential
//            }
//        }
//
//        class GithubAuthProvider : AuthProvider {
//            companion object {
//                fun credential(token: String): AuthCredential
//            }
//        }
//
//        class GoogleAuthProvider : AuthProvider {
//            companion object {
//                fun credential(idToken: String?, accessToken: String?): AuthCredential
//            }
//        }
//
//        open class OAuthProvider(providerId: String) : AuthProvider {
//            val providerId: String
//            fun credential(optionsOrIdToken: Any?, accessToken: String?): AuthCredential
//
//            fun addScope(scope: String)
//            fun setCustomParameters(customOAuthParameters: Map<String, String>)
//        }
//
//        interface OAuthCredentialOptions {
//            val accessToken: String?
//            val idToken: String?
//            val rawNonce: String?
//        }
//
//        class PhoneAuthProvider(auth: Auth?) : AuthProvider {
//            companion object {
//                fun credential(verificationId: String, verificationCode: String): AuthCredential
//            }
//            fun verifyPhoneNumber(phoneInfoOptions: String, applicationVerifier: ApplicationVerifier): Promise<String>
//        }
//
//        abstract class ApplicationVerifier {
//            val type: String
//            fun verify(): Promise<String>
//        }
//
//        class TwitterAuthProvider : AuthProvider {
//            companion object {
//                fun credential (token: String, secret: String): AuthCredential
//            }
//        }
//    }
//
//    fun User(a: Any,b: Any,c: Any): user.User
//
//    object user {
//        abstract class User {
//            val uid: String
//            val displayName: String?
//            val email: String?
//            val emailVerified: Boolean
//            val metadata: UserMetadata
//            val multiFactor: multifactor.MultiFactorUser
//            val phoneNumber: String?
//            val photoURL: String?
//            val providerData: Array<UserInfo>
//            val providerId: String
//            val refreshToken: String
//            val tenantId: String?
//            val isAnonymous: Boolean
//
//            fun delete(): Promise<Unit>
//            fun getIdToken(forceRefresh: Boolean?): Promise<String>
//            fun getIdTokenResult(forceRefresh: Boolean?): Promise<auth.IdTokenResult>
//            fun linkWithCredential(credential: auth.AuthCredential): Promise<auth.AuthResult>
//            fun reauthenticateWithCredential(credential: auth.AuthCredential): Promise<auth.AuthResult>
//            fun reload(): Promise<Unit>
//            fun sendEmailVerification(actionCodeSettings: Any?): Promise<Unit>
//            fun unlink(providerId: String): Promise<User>
//            fun updateEmail(newEmail: String): Promise<Unit>
//            fun updatePassword(newPassword: String): Promise<Unit>
//            fun updatePhoneNumber(phoneCredential: auth.AuthCredential): Promise<Unit>
//            fun updateProfile(profile: ProfileUpdateRequest): Promise<Unit>
//            fun verifyBeforeUpdateEmail(newEmail: String, actionCodeSettings: Any?): Promise<Unit>
//        }
//
//        abstract class UserMetadata {
//            val creationTime: String?
//            val lastSignInTime: String?
//        }
//
//        abstract class UserInfo {
//            val displayName: String?
//            val email: String?
//            val phoneNumber: String?
//            val photoURL: String?
//            val providerId: String
//            val uid: String
//        }
//
//        interface ProfileUpdateRequest {
//            val displayName: String?
//            val photoURL: String?
//        }
//
//    }

//    object multifactor {
//        abstract class MultiFactorUser {
//            val enrolledFactors: Array<MultiFactorInfo>
//
//            fun enroll(assertion: MultiFactorAssertion, displayName: String?): Promise<Unit>
//            fun getSession(): Promise<MultiFactorSession>
//            fun unenroll(option: MultiFactorInfo): Promise<Unit>
//            fun unenroll(option: String): Promise<Unit>
//        }
//
//        abstract class MultiFactorInfo {
//            val displayName: String?
//            val enrollmentTime: String
//            val factorId: String
//            val uid: String
//        }
//
//        abstract class MultiFactorAssertion {
//            val factorId: String
//        }
//
//        interface MultiFactorSession
//
//        abstract class MultifactorResolver {
//
//            val auth: auth.Auth
//            val hints: Array<MultiFactorInfo>
//            val session: MultiFactorSession
//
//            fun resolveSignIn(assertion: MultiFactorAssertion): Promise<auth.AuthResult>
//        }
//    }

//    fun functions(app: App? = definedExternally): functions.Functions
//
//    object functions {
//        class Functions {
//            fun httpsCallable(name: String, options: Json?): HttpsCallable
//            fun useFunctionsEmulator(origin: String)
//            fun useEmulator(host: String, port: Int)
//        }
//        interface HttpsCallableResult {
//            val data: Any?
//        }
//        interface HttpsCallable {
//        }
//
//    }
//
//    fun database(app: App? = definedExternally): database.Database
//
//    object database {
//        fun enableLogging(logger: Boolean?, persistent: Boolean? = definedExternally)
//
//        open class Database {
//            fun ref(path: String? = definedExternally): Reference
//            fun useEmulator(host: String, port: Int)
//        }
//        open class ThenableReference : Reference
//
//
//        open class Query {
//            fun on(eventType: String?, callback: SnapshotCallback, cancelCallbackOrContext: (error: Error) -> Unit? = definedExternally, context: Any? = definedExternally): SnapshotCallback
//            fun off(eventType: String?, callback: SnapshotCallback?, context: Any? = definedExternally)
//            fun once(eventType: String, callback: SnapshotCallback, failureCallbackOrContext: (error: Error) -> Unit? = definedExternally, context: Any? = definedExternally): SnapshotCallback
//            fun orderByChild(path: String): Query
//            fun orderByKey(): Query
//            fun orderByValue(): Query
//            fun startAt(value: Any, key: String? = definedExternally): Query
//            fun endAt(value: Any, key: String? = definedExternally): Query
//            fun equalTo(value: Any, key: String? = definedExternally): Query
//            fun limitToFirst(limit: Int): Query
//            fun limitToLast (limit: Int): Query
//        }
//
//        open class Reference: Query {
//            val key: String?
//            fun child(path: String): Reference
//            fun remove(): Promise<Unit>
//            fun onDisconnect(): OnDisconnect
//            fun update(value: Any?): Promise<Unit>
//            fun set(value: Any?): Promise<Unit>
//            fun push(): ThenableReference
//            fun <T> transaction(
//                transactionUpdate: (currentData: T) -> T,
//                onComplete: (error: Error?, committed: Boolean, snapshot: DataSnapshot?) -> Unit,
//                applyLocally: Boolean?
//            ): Promise<T>
//        }
//
//        open class DataSnapshot {
//            val key: String?
//            fun `val`(): Any
//            fun exists(): Boolean
//            fun forEach(action: (a: DataSnapshot) -> Boolean): Boolean
//            fun numChildren(): Int
//            fun child(path: String): DataSnapshot
//        }
//
//        open class OnDisconnect {
//            fun update(value: Any?): Promise<Unit>
//            fun remove(): Promise<Unit>
//            fun cancel(): Promise<Unit>
//            fun set(value: Any?): Promise<Unit>
//        }
//
//        object ServerValue {
//            val TIMESTAMP: Any
//            fun increment (delta: Double): Any
//        }
//    }
//
//    fun remoteConfig(app: App? = definedExternally): remoteConfig.RemoteConfig
//
//    object remoteConfig {
//        interface RemoteConfig {
//            var defaultConfig: Any
//            var fetchTimeMillis: Long
//            var lastFetchStatus: String
//            val settings: Settings
//            fun activate(): Promise<Boolean>
//            fun ensureInitialized(): Promise<Unit>
//            fun fetch(): Promise<Unit>
//            fun fetchAndActivate(): Promise<Boolean>
//            fun getAll(): Json
//            fun getBoolean(key: String): Boolean
//            fun getNumber(key: String): Number
//            fun getString(key: String): String?
//            fun getValue(key: String): Value
//        }
//
//        interface Settings {
//            var fetchTimeoutMillis: Number
//            var minimumFetchIntervalMillis: Number
//        }
//
//        interface Value {
//            fun asBoolean(): Boolean
//            fun asNumber(): Number
//            fun asString(): String?
//            fun getSource(): String
//        }
//    }
//
//    fun installations(app: App? = definedExternally): installations.Installations
//
//    object installations {
//        interface Installations {
//            fun delete(): Promise<Unit>
//            fun getId(): Promise<String>
//            fun getToken(forceRefresh: Boolean): Promise<String>
//        }
//    }
//
//    fun performance(app: App? = definedExternally): performance
//
//    object performance {
//
//        var dataCollectionEnabled: Boolean
//        var instrumentationEnabled: Boolean
//
//        fun trace(
//            name: String
//            ): PerformanceTrace
//
//        open class Performance {
//
//        }
//
//        open class PerformanceTrace {
//            fun start()
//            fun stop()
//
//            fun getAttribute(attr: String): String?
//            fun getMetric(metricName: String): Number
//            fun incrementMetric(metricName: String, num: Number)
//            fun putAttribute(attr: String, value: String)
//            fun putMetric(metricName: String, num: Number)
//            fun removeAttribute(attr: String)
//        }
//    }
//}
