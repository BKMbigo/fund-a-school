package com.github.bkmbigo.fundaschool.di

import com.github.bkmbigo.fundaschool.data.square.api.installContentNegotiation
import com.github.bkmbigo.fundaschool.data.square.api.installLogging
import com.github.bkmbigo.fundaschool.data.square.api.installResponseObserver
import dev.gitlive.firebase.*
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import org.koin.dsl.module

actual val expectedModule = module {
    factory<FirebaseAuth> { Firebase.auth }
    single<FirebaseFirestore> { Firebase.firestore }

    single {
        HttpClient(Android){
            installLogging()
            installContentNegotiation()
            installResponseObserver()
        }
    }
}