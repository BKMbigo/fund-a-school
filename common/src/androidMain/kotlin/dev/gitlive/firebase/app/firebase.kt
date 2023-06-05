/*
 * Copyright (c) 2020 GitLive Ltd.  Use of this source code is governed by the Apache 2.0 license.
 */

package dev.gitlive.firebase

import android.content.Context

actual typealias FirebaseException = com.google.firebase.FirebaseException

actual typealias FirebaseNetworkException = com.google.firebase.FirebaseNetworkException

actual typealias FirebaseTooManyRequestsException = com.google.firebase.FirebaseTooManyRequestsException

actual typealias FirebaseApiNotAvailableException = com.google.firebase.FirebaseApiNotAvailableException

val Firebase.app: FirebaseApp
    get() = FirebaseApp(com.google.firebase.FirebaseApp.getInstance())

actual fun Firebase.app(name: String): FirebaseApp =
    FirebaseApp(com.google.firebase.FirebaseApp.getInstance(name))

actual fun Firebase.initialize(context: Any?): FirebaseApp? =
    com.google.firebase.FirebaseApp.initializeApp(context as Context)?.let { FirebaseApp(it) }

actual fun Firebase.initialize(context: Any?, options: FirebaseOptions, name: String): FirebaseApp =
    FirebaseApp(com.google.firebase.FirebaseApp.initializeApp(context as Context, options.toAndroid(), name))

actual fun Firebase.initialize(context: Any?, options: FirebaseOptions) =
    FirebaseApp(com.google.firebase.FirebaseApp.initializeApp(context as Context, options.toAndroid()))

actual fun Firebase.apps(context: Any?) = com.google.firebase.FirebaseApp.getApps(context as Context)
    .map { FirebaseApp(it) }

private fun FirebaseOptions.toAndroid() = com.google.firebase.FirebaseOptions.Builder()
    .setApplicationId(appId)
    .setApiKey(apiKey)
    .setDatabaseUrl(databaseUrl)
    .setGaTrackingId(measurementId)
    .setStorageBucket(storageBucket)
    .setProjectId(projectId)
    .setGcmSenderId(messagingSenderId)
    .build()