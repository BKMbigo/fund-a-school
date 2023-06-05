package com.github.bkmbigo.fundaschool.data.square.api

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.headers

fun HttpRequestBuilder.attachHeaders() {
    headers.clear()
    headers {
        headers.append("Square-Version", "2023-05-17") // API Key should be obscured from version control
        headers.append("Authorization", "Bearer ${SquareAccessToken}")
        headers.append("Content-Type", "application/json")
    }
}