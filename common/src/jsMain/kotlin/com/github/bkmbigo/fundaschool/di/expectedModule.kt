package com.github.bkmbigo.fundaschool.di

import com.github.bkmbigo.fundaschool.domain.repositories.AuthRepository
import dev.gitlive.firebase.*
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import org.koin.dsl.module

actual val expectedModule = module {
    val firebaseApp = Firebase.initialize(null, FirebaseConfig, "my_app")

    single { firebaseApp }
    single { Firebase.auth }
    single { Firebase.firestore }

}