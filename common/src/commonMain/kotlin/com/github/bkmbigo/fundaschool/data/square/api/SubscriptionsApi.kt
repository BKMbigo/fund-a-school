package com.github.bkmbigo.fundaschool.data.square.api

import com.github.bkmbigo.fundaschool.data.square.dto.CreateSubscriptionDTO
import com.github.bkmbigo.fundaschool.data.square.dto.UpsertCatalogDTO
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

class SubscriptionsApi(
    private val client: HttpClient
) {
    suspend fun createCatalog(
        createCatalogDTO: UpsertCatalogDTO
    ): HttpResponse {
        return client.post("${ApiEndpoints.HOST_URL}v2/catalog/object") {
            attachHeaders()
            contentType(ContentType.Application.Json)
            setBody(createCatalogDTO)
        }
    }

    suspend fun createSubscription(createSubscriptionDTO: CreateSubscriptionDTO): HttpResponse {
        return client.post("${ApiEndpoints.HOST_URL}v2/subscriptions") {
            attachHeaders()
            contentType(ContentType.Application.Json)
            setBody(createSubscriptionDTO)
        }
    }


}