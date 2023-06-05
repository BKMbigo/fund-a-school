package com.github.bkmbigo.fundaschool.data.square.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js

actual val ktorClient: HttpClient = HttpClient(Js) {
    installLogging()
    installContentNegotiation()
    installResponseObserver()
}