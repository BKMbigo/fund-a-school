package com.github.bkmbigo.fundaschool.di

import dev.gitlive.firebase.*
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import org.koin.dsl.module

actual val expectedModule = module {
    factory<FirebaseAuth> { Firebase.auth }

    single<FirebaseFirestore> { Firebase.firestore }
}