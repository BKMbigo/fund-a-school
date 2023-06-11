package com.github.bkmbigo.fundaschool.data.square.dto

import com.github.bkmbigo.fundaschool.domain.models.square.PaymentStatus
import kotlinx.serialization.Serializable

@Serializable
data class PaymentsDTO(
    val id: String,
    val created_at: String,
    val amount_money: MoneyDTO,
    val status: PaymentStatus,
    val buyer_email_address: String? = null,
    val customerId: String? = null,
    val reference_id: String? = null
)