package com.github.bkmbigo.fundaschool.data.square.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateSubscriptionDTO(
    val idempotency_key: String,
    val location_id: String,
    val plan_id: String,
    val customer_id: String? = null,
    val start_date: String? = null,
    val canceled_date: String? = null,
    val card_id: String? = null
)
