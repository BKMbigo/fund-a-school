package com.github.bkmbigo.fundaschool.presentation.utils

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

suspend fun HttpClient.getBytesProvider(url: String) =
    this.get { url(url)  }.readBytes()