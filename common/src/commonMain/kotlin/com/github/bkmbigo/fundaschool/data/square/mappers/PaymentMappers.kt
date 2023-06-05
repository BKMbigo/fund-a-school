package com.github.bkmbigo.fundaschool.data.square.mappers

import com.github.bkmbigo.fundaschool.data.square.dto.CreatePaymentDTO
import com.github.bkmbigo.fundaschool.data.square.dto.MoneyDTO
import com.github.bkmbigo.fundaschool.data.square.dto.PaymentsDTO
import com.github.bkmbigo.fundaschool.data.square.utils.generateUUID
import com.github.bkmbigo.fundaschool.domain.models.square.Payment
import kotlinx.datetime.Instant

fun Payment.toCreatePaymentDTO(
    sourceId: String
) = CreatePaymentDTO(
    source_id = sourceId,
    idempotency_key = generateUUID(),
    amount_money = MoneyDTO(amount = amount * 100,),
    customer_id = customerId,
    reference_id = referenceId,
    buyer_email_address = emailAddress
)

fun PaymentsDTO.toPayment() = Payment(
    id = id,
    createdAt = Instant.parse(created_at),
    amount = amount_money.amount / 100,
    customerId = customerId,
    referenceId = reference_id,
    emailAddress = buyer_email_address
)