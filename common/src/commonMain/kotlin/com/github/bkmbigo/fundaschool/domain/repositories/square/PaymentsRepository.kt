package com.github.bkmbigo.fundaschool.domain.repositories.square

import com.github.bkmbigo.fundaschool.domain.models.square.Payment

interface PaymentsRepository {
    suspend fun createPayment(
        sourceId: String,
        amount: Long,
        customerId: String,
        referenceId: String,
        emailAddress: String
    ): SquareResponse<Payment>

}