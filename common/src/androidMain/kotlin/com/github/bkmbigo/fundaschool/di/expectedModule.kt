package com.github.bkmbigo.fundaschool.di

import com.github.bkmbigo.fundaschool.domain.repositories.AuthRepository
import firebase.firestore.firestore
import com.google.firebase.auth.FirebaseAuth
import com.github.bkmbigo.fundaschool.features.firebase.dependencies.app.Firebase
import firebase.app.app
import firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

actual val expectedModule = module {
    val firebaseApp = Firebase.app
    single<FirebaseAuth> { FirebaseAuth.getInstance(firebaseApp.android) }
    single<LocalFirebaseAuth> { LocalFirebaseAuth(get()) }
    single<firebase.firestore.FirebaseFirestore> { Firebase.firestore(firebaseApp) }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
}