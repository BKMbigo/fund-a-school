package com.github.bkmbigo.fundaschool.data.square.repositories

import com.github.bkmbigo.fundaschool.data.square.api.PaymentsAPI
import com.github.bkmbigo.fundaschool.data.square.dto.CreatePaymentDTO
import com.github.bkmbigo.fundaschool.data.square.dto.MoneyDTO
import com.github.bkmbigo.fundaschool.data.square.dto.PaymentResponseDTO
import com.github.bkmbigo.fundaschool.data.square.mappers.toPayment
import com.github.bkmbigo.fundaschool.data.square.models.SquareError
import com.github.bkmbigo.fundaschool.data.square.utils.generateUUID
import com.github.bkmbigo.fundaschool.data.square.utils.safeApiCall
import com.github.bkmbigo.fundaschool.data.square.utils.toElement
import com.github.bkmbigo.fundaschool.domain.models.square.Payment
import com.github.bkmbigo.fundaschool.domain.repositories.square.PaymentsRepository
import com.github.bkmbigo.fundaschool.domain.repositories.square.SquareResponse
import com.github.bkmbigo.squareinappkotlin.data.network.dto.payments.ErrorCategory
import com.github.bkmbigo.squareinappkotlin.data.network.dto.payments.ErrorCode
import io.ktor.client.call.body

class PaymentRepositoryImpl(
    private val paymentsApi: PaymentsAPI
): PaymentsRepository {
    override suspend fun createPayment(
        sourceId: String,
        amount: Long,
        customerId: String,
        referenceId: String,
        emailAddress: String
    ): SquareResponse<Payment> = safeApiCall {
        val response = paymentsApi.createPayment(
            CreatePaymentDTO(
                source_id = sourceId,
                idempotency_key = generateUUID(),
                amount_money = MoneyDTO(
                    amount = amount * 100f
                ),
                customer_id = customerId,
                reference_id = referenceId,
                buyer_email_address = emailAddress
            )
        ).body<PaymentResponseDTO>()

        if (response.payment != null) {
            SquareResponse.Success<Payment>(response.payment.toPayment())
        } else {
            response.errors?.let { SquareResponse.Error<Payment>(it) } ?: SquareResponse.Error(listOf(SquareError(ErrorCategory.API_ERROR, ErrorCode.INVALID_POSTAL_CODE, null, null)))
        }
    }.toElement() ?: SquareResponse.Error(listOf(SquareError(ErrorCategory.API_ERROR, ErrorCode.INVALID_POSTAL_CODE, null, null)))
}