package com.github.bkmbigo.fundaschool.data.square.api

import com.github.bkmbigo.fundaschool.data.square.dto.CreatePaymentDTO
import com.github.bkmbigo.fundaschool.data.square.utils.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

class PaymentsAPI(
    private val client: HttpClient = ktorClient
) {

    /**
     * Create a Purchase
     */
    suspend fun createPayment(createPaymentDTO: CreatePaymentDTO): HttpResponse {
        return client.post("${ApiEndpoints.HOST_URL}v2/payments") {
            attachHeaders()
            contentType(ContentType.Application.Json)
            setBody(createPaymentDTO)
        }
    }

    suspend fun listPayments(
        last_4: String? = null,
        card_brand: String? = null
    ) = client.get("${ApiEndpoints.HOST_URL}v2/payments") {
        url {
            last_4?.let { it1 -> parameters.append("last_4", it1) }
            card_brand?.let { it1 -> parameters.append("card_brand", it1) }
        }
        attachHeaders()
    }
}