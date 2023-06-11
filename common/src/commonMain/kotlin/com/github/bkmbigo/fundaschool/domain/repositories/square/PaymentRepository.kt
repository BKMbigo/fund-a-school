package com.github.bkmbigo.fundaschool.domain.repositories.square

import com.github.bkmbigo.fundaschool.domain.models.square.Payment

interface PaymentRepository {
    suspend fun createPayment(
        sourceId: String,
        amount: Float?,
        customerId: String,
        referenceId: String,
        emailAddress: String
    ): SquareResponse<Payment>

}