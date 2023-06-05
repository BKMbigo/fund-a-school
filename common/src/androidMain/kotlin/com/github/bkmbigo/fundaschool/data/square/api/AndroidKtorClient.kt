package com.github.bkmbigo.fundaschool.data.square.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android

actual val ktorClient: HttpClient = HttpClient(Android) {
    installLogging()
    installContentNegotiation()
    installResponseObserver()
}