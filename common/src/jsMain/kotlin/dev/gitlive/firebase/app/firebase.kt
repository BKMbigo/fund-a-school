/*
 * Copyright (c) 2020 GitLive Ltd.  Use of this source code is governed by the Apache 2.0 license.
 */

package dev.gitlive.firebase

import external.firebase.app.FirebaseAppSettings
import kotlinx.coroutines.await
import kotlin.js.json

//actual val Firebase.app: FirebaseApp
//    get() = FirebaseApp(external.firebase.app.getApp())

actual fun Firebase.app(name: String): FirebaseApp =
    FirebaseApp(external.firebase.app.getApp(name))

actual fun Firebase.initialize(context: Any?): FirebaseApp? =
    throw UnsupportedOperationException("Cannot initialize firebase without options in JS")

actual fun Firebase.initialize(context: Any?, options: FirebaseOptions, name: String): FirebaseApp =
    FirebaseApp(external.firebase.app.initializeApp(options.toJson()))

actual fun Firebase.initialize(context: Any?, options: FirebaseOptions) =
    FirebaseApp(external.firebase.app.initializeApp(options.toJson()))

actual class FirebaseApp internal constructor(val js: external.firebase.app.FirebaseApp) {
    actual val name: String
        get() = js.name
//    actual val options: FirebaseOptions
//        get() = js.options.run {
//            FirebaseOptions(
//                appId,
//                apiKey = apiKey,
//                databaseUrl = databaseURL,
//                measurementId = measurementId,
//                storageBucket = storageBucket,
//                projectId = projectId,
//                messagingSenderId = messagingSenderId,
//                authDomain = authDomain
//            )
//        }
//
//    actual suspend fun delete() = external.firebase.app.deleteApp(js).await()
}

actual fun Firebase.apps(context: Any?) = external.firebase.app.getApps().map { FirebaseApp(it) }

fun FirebaseOptions.toJson() = json(
    "apiKey" to apiKey,
    "appId" to appId,
    "databaseURL" to (databaseUrl ?: undefined),
    "storageBucket" to storageBucket,
    "projectId" to projectId,
    "measurementId" to measurementId,
    "messagingSenderId" to messagingSenderId,
    "authDomain" to authDomain
)

private fun FirebaseOptions.toJsOptions() = object: external.firebase.app.FirebaseOptions{
    override val apiKey: String = this@toJsOptions.apiKey
    override val appId: String = this@toJsOptions.appId
    override val authDomain: String = this@toJsOptions.authDomain ?: ""
    override val databaseURL: String = this@toJsOptions.databaseUrl ?: ""
    override val measurementId: String = this@toJsOptions.measurementId ?: ""
    override val messagingSenderId: String = this@toJsOptions.messagingSenderId
    override val projectId: String = this@toJsOptions.projectId
    override val storageBucket: String = this@toJsOptions.storageBucket
}

actual open class FirebaseException(code: String?, cause: Throwable) : Exception("$code: ${cause.message}", cause)
actual open class FirebaseNetworkException(code: String?, cause: Throwable) : FirebaseException(code, cause)
actual open class FirebaseTooManyRequestsException(code: String?, cause: Throwable) : FirebaseException(code, cause)
actual open class FirebaseApiNotAvailableException(code: String?, cause: Throwable) : FirebaseException(code, cause)
