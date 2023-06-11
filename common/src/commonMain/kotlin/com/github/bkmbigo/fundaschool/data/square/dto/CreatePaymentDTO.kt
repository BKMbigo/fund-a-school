package com.github.bkmbigo.fundaschool.data.square.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreatePaymentDTO(
    val source_id: String,
    val idempotency_key: String,
    val amount_money: MoneyDTO,
    val customer_id: String? = null,
    val reference_id: String,
    val buyer_email_address: String
)
