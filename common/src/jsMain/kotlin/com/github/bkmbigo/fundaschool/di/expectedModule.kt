package com.github.bkmbigo.fundaschool.di

import dev.gitlive.firebase.*
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import org.koin.dsl.module

actual val expectedModule = module {
//    val firebaseApp = external.firebase.app.initializeApp(options = FirebaseConfig.toJson())
//    val auth = external.firebase.auth.initializeAuth(firebaseApp)
//    val firestore = external.firebase.firestore.getFirestore(firebaseApp)
    val firebaseApp = Firebase.initialize(null, FirebaseConfig)

    single { firebaseApp }
    single { Firebase.auth(firebaseApp) }
    single { Firebase.firestore(firebaseApp) }

    single { HttpClient(Js) }
}